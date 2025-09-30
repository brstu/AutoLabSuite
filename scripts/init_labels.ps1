<#!
.SYNOPSIS
  Инициализация стандартных GitHub labels для репозитория AutoLabSuite.
.DESCRIPTION
  Скрипт создаёт (или обновляет) набор label'ов через GitHub REST API.
  Требуется переменная окружения GITHUB_TOKEN с правами repo.
.PARAMETER Owner
  Владелец (организация или пользователь)
.PARAMETER Repo
  Название репозитория
.EXAMPLE
  ./init_labels.ps1 -Owner my-org -Repo AutoLabSuite
#>
param(
  [Parameter(Mandatory=$true)][string]$Owner,
  [Parameter(Mandatory=$true)][string]$Repo
)

if (-not $env:GITHUB_TOKEN) {
  Write-Error "GITHUB_TOKEN не установлен"; exit 1
}

$Headers = @{ Authorization = "token $($env:GITHUB_TOKEN)"; Accept = 'application/vnd.github+json' }
$ApiBase = "https://api.github.com/repos/$Owner/$Repo"

$Labels = @(
  @{ name='fe'; color='1E90FF'; description='Frontend tasks (NeonFox)' },
  @{ name='be'; color='5319E7'; description='Backend tasks (ByteForge)' },
  @{ name='ml'; color='A333D0'; description='ML / Data (VectorPulse)' },
  @{ name='devops'; color='0E8A16'; description='Infra / CI (CloudNova)' },
  @{ name='core'; color='24292F'; description='Core / product / architecture' },
  @{ name='feature'; color='0E8A16'; description='New feature' },
  @{ name='bug'; color='D73A4A'; description='Bug / defect' },
  @{ name='docs'; color='0075CA'; description='Documentation' },
  @{ name='chore'; color='BFD4F2'; description='Chore / maintenance' },
  @{ name='refactor'; color='A2EEEF'; description='Refactor' },
  @{ name='test'; color='E4E669'; description='Tests / coverage' },
  @{ name='spike'; color='5319E7'; description='Research / spike' },
  @{ name='security'; color='B60205'; description='Security related' },
  @{ name='perf'; color='FBCA04'; description='Performance' },
  @{ name='blocked'; color='B60205'; description='Blocked item' },
  @{ name='ready'; color='0E8A16'; description='Ready for sprint' },
  @{ name='review'; color='5319E7'; description='In review' },
  @{ name='qa'; color='FBCA04'; description='QA validation' },
  @{ name='risk'; color='8B949E'; description='Identified risk' },
  @{ name='tech-debt'; color='8B949E'; description='Technical debt' },
  @{ name='hotfix'; color='D73A4A'; description='Hotfix / urgent' },
  @{ name='regression'; color='D73A4A'; description='Regression' },
  @{ name='backlog'; color='C5DEF5'; description='Backlog pool' },
  @{ name='long-term'; color='C5DEF5'; description='Long term idea' },
  @{ name='p0'; color='B60205'; description='Critical priority' },
  @{ name='p1'; color='D93F0B'; description='High priority' },
  @{ name='p2'; color='FBCA04'; description='Medium priority' },
  @{ name='p3'; color='0E8A16'; description='Low priority' }
)

foreach ($label in $Labels) {
  $url = "$ApiBase/labels/$($label.name)"
  try {
    $existing = Invoke-RestMethod -Method Get -Uri $url -Headers $Headers -ErrorAction SilentlyContinue
  } catch { $existing = $null }
  $payload = @{ name=$label.name; color=$label.color; description=$label.description } | ConvertTo-Json
  if ($existing) {
    Write-Host "Updating label $($label.name)"
    Invoke-RestMethod -Method Patch -Uri $url -Headers $Headers -Body $payload | Out-Null
  } else {
    Write-Host "Creating label $($label.name)"
    Invoke-RestMethod -Method Post -Uri "$ApiBase/labels" -Headers $Headers -Body $payload | Out-Null
  }
}

Write-Host "Labels sync complete."
