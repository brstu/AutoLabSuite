<#
Smoke tests for Kubeflow deployment.

Checks:
 - Namespace `kubeflow` exists
 - At least one pod in `kubeflow` is Running
 - Optional: curl the UI URL

Usage:
  .\smoke_test.ps1 -Kubeconfig C:\path\to\kubeconfig -UiUrl https://kubeflow.example.com
#>
param(
    [string]$Kubeconfig,
    [string]$UiUrl
)

if ($Kubeconfig) { $env:KUBECONFIG = $Kubeconfig }

Write-Output "Checking for 'kubeflow' namespace"
kubectl get namespace kubeflow --ignore-not-found | Out-Null
if ($LASTEXITCODE -ne 0) {
    Write-Error "Namespace 'kubeflow' not found"
    exit 2
}

Write-Output "Waiting for pods in 'kubeflow' to be ready (timeout 300s)"
kubectl wait --for=condition=ready pod --all -n kubeflow --timeout=300s
if ($LASTEXITCODE -ne 0) {
    Write-Error "Some pods in 'kubeflow' did not reach Ready state in time"
    kubectl get pods -n kubeflow
    exit 3
}

Write-Output "At least one pod is ready in 'kubeflow'"

if ($UiUrl) {
    Write-Output "Checking Kubeflow UI at $UiUrl"
    try {
        $resp = Invoke-WebRequest -Uri $UiUrl -UseBasicParsing -TimeoutSec 15
        if ($resp.StatusCode -ge 200 -and $resp.StatusCode -lt 400) {
            Write-Output "Kubeflow UI responded with $($resp.StatusCode)"
        } else {
            Write-Error "Kubeflow UI returned status $($resp.StatusCode)"
            exit 4
        }
    } catch {
        Write-Error "Failed to reach Kubeflow UI: $_"
        exit 5
    }
}

Write-Output "Smoke tests passed"
