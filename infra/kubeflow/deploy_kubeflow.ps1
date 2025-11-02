<#
Deploy Kubeflow using vendor manifests and kustomize.

This script expects that `kubectl` and `kustomize` are installed and in PATH.
It will clone the manifests (via vendor_manifests.ps1) if not present, then build and apply
the kustomize path you provide.

Usage:
  .\deploy_kubeflow.ps1 -Kubeconfig C:\path\to\kubeconfig -KustomizePath vendor/manifests/path/to/kustomization

Parameters:
  -Kubeconfig: optional path to kubeconfig file (if not provided will use default KUBECONFIG env or $HOME/.kube/config)
  -KustomizePath: path under infra/kubeflow/ that points to a kustomization (required)
  -Environment: staging|prod (optional) — will use overlays/<env> if present
  -UiUrl: optional URL to check in smoke test (e.g., https://kubeflow.example.com)
#>
param(
    [string]$Kubeconfig,
    [string]$KustomizePath,
    [ValidateSet('staging','prod')][string]$Environment,
    [string]$UiUrl
)

Set-StrictMode -Version Latest

$ScriptRoot = Split-Path -Parent $MyInvocation.MyCommand.Definition

if (-not (Get-Command kubectl -ErrorAction SilentlyContinue)) {
    Write-Error "kubectl is required in PATH"
    exit 1
}
if (-not (Get-Command kustomize -ErrorAction SilentlyContinue)) {
    Write-Error "kustomize is required in PATH"
    exit 1
}

Write-Output "Ensuring vendor manifests are present"
& "$ScriptRoot/vendor_manifests.ps1"

if (-not $KustomizePath) {
    Write-Error "You must provide -KustomizePath pointing to a kustomization path under infra/kubeflow/vendor/manifests or to an overlay in infra/kubeflow/overlays"
    exit 1
}

# Resolve final kustomize target
if ($Environment) {
    # if overlays exist, prefer overlays/<env>
    $overlay = Join-Path $ScriptRoot "overlays/$Environment"
    if (Test-Path $overlay) {
        Write-Output "Using overlay: $overlay"
        $target = $overlay
    } else {
        $target = Join-Path $ScriptRoot $KustomizePath
    }
} else {
    $target = Join-Path $ScriptRoot $KustomizePath
}

if (-not (Test-Path $target)) {
    Write-Error "Target kustomize path not found: $target"
    exit 1
}

if ($Kubeconfig) {
    $env:KUBECONFIG = $Kubeconfig
    Write-Output "Using KUBECONFIG: $Kubeconfig"
}

Write-Output "Building manifests from: $target"
$manifest = & kustomize build $target
if ($LASTEXITCODE -ne 0) { Write-Error "kustomize build failed"; exit 1 }

Write-Output "Applying manifests to cluster"
$manifest | kubectl apply -f -
if ($LASTEXITCODE -ne 0) { Write-Error "kubectl apply failed"; exit 1 }

Write-Output "Running smoke tests"
& "$ScriptRoot/smoke_test.ps1" -Kubeconfig ($Kubeconfig) -UiUrl $UiUrl
