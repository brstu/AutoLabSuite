<#
Clone or update Kubeflow manifests into infra/kubeflow/vendor/manifests

Usage:
  .\vendor_manifests.ps1

This script clones the official `kubeflow/manifests` repo into `infra/kubeflow/vendor/manifests`
or pulls latest changes if the checkout already exists.
#>
param()

$RepoUrl = 'https://github.com/kubeflow/manifests.git'
$VendorDir = Join-Path -Path $PSScriptRoot -ChildPath 'vendor/manifests'

if (-not (Get-Command git -ErrorAction SilentlyContinue)) {
    Write-Error "git is required but not found in PATH"
    exit 1
}

if (Test-Path $VendorDir) {
    Write-Output "Updating existing manifests in $VendorDir"
    Push-Location $VendorDir
    git fetch --all --prune
    git reset --hard origin/main
    Pop-Location
} else {
    Write-Output "Cloning manifests into $VendorDir"
    git clone $RepoUrl $VendorDir
}

Write-Output "Vendor manifests ready at: $VendorDir"
