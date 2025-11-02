Ansible layout for AutoLabSuite

This folder contains example Ansible configuration and roles used to bootstrap servers for Kubernetes (control-plane and worker nodes), install Istio on the control plane, and distribute SSH keys to hosts.

Structure

- `inventory/hosts.ini`              - example inventory (fill with your hosts)
- `ansible.cfg`                      - recommended Ansible configuration for this repo
- `group_vars/all.yml`               - shared variables (tune as needed)
- `playbooks/setup_ssh_keys.yml`     - playbook that installs an SSH public key on target machines
- `playbooks/bootstrap_kubernetes.yml` - playbook to bootstrap basic Kubernetes prerequisites on nodes and install Istio on control-plane
- `roles/ssh_key/`                   - role that installs an authorized SSH public key for a user
  - `defaults/main.yml`
  - `tasks/main.yml`
  - `files/id_rsa.pub`               - placeholder public key (replace with your real key)
- `roles/kubernetes/`                - role to perform minimal node bootstrap for Debian/Ubuntu
  - `tasks/main.yml`
- `roles/istio/`                     - role to download `istioctl` and install Istio control plane
  - `defaults/main.yml`
  - `tasks/main.yml`

Usage (example)

1. Edit `inventory/hosts.ini` and fill with your hosts/IPs and groups.

2. Provide your SSH private key for Ansible control (or use an existing agent/session).

3. Distribute SSH key to all hosts (will install public key into target user accounts):

```powershell
ansible-playbook -i infra/ansible/inventory/hosts.ini infra/ansible/playbooks/setup_ssh_keys.yml --ask-become-pass
```

4. Bootstrap Kubernetes prerequisites and install Istio on control-plane nodes:

```powershell
ansible-playbook -i infra/ansible/inventory/hosts.ini infra/ansible/playbooks/bootstrap_kubernetes.yml --ask-become-pass
```

5. Initialize control-plane, install CNI (Calico), and produce join command:

```powershell
ansible-playbook -i infra/ansible/inventory/hosts.ini infra/ansible/playbooks/init_control_plane.yml --ask-become-pass
```

6. Join workers to the cluster:

```powershell
ansible-playbook -i infra/ansible/inventory/hosts.ini infra/ansible/playbooks/join_workers.yml --ask-become-pass
```
Notes and best-practices
- The `roles/istio` role uses `istioctl` to perform the installation. The default Istio version is set in `roles/istio/defaults/main.yml` and can be overridden in `group_vars` or host vars.
- Replace the placeholder public key in `roles/ssh_key/files/id_rsa.pub` with your real public key or point the role variable to another file.
- Use Ansible Vault for sensitive data (SSH private keys, cloud credentials).
- The provided `roles/kubernetes` role is minimal and targets Debian/Ubuntu. If you need RHEL/CentOS support or a different container runtime, I can extend the role.

Sudo / admin user behavior
- By default the `ssh_key` role will ensure the `target_user` exists and create a sudoers file under `/etc/sudoers.d/` which grants passwordless sudo (root-like privileges). Control with variables in `roles/ssh_key/defaults/main.yml`:
  - `ensure_sudo_user` (default: true) — create the user if missing and manage sudoers file
  - `sudo_nopasswd` (default: true) — grant NOPASSWD sudo in the sudoers file
  - `sudoers_file` — path of the sudoers file created (default: `/etc/sudoers.d/{{ target_user }}`)

If you don't want the role to modify users or sudoers, set `ensure_sudo_user: false` in your inventory or playbook.

Create a dedicated admin user (recommended)
- You can create a dedicated admin account and deploy SSH keys to it using the included playbook `playbooks/create_admin_user.yml`. That playbook creates the account (default `ansible-admin`), grants passwordless sudo and deploys the public key via the `ssh_key` role.

Run it like this:

```powershell
ansible-playbook -i infra/ansible/inventory/hosts.ini infra/ansible/playbooks/create_admin_user.yml --ask-become-pass
```

Kubeflow vendor/deploy scripts
- The repo includes a minimal vendor + deploy script under `infra/kubeflow/`.
  - `vendor_manifests.ps1` — clones or updates `https://github.com/kubeflow/manifests` into `infra/kubeflow/vendor/manifests`.
  - `deploy_kubeflow.ps1` — builds and applies a kustomize path (use `-KustomizePath` or an overlay `-Environment staging|prod`). It calls `smoke_test.ps1` after apply.
  - `smoke_test.ps1` — waits for pods in namespace `kubeflow` and optionally curls a UI URL.

Quick example (PowerShell):

```powershell
# 1) vendor manifests (clone/update)
.\infra\kubeflow\vendor_manifests.ps1

# 2) edit infra/kubeflow/base/kustomization.yaml to point to a kustomize directory under infra/kubeflow/vendor/manifests

# 3) deploy (use overlays or point to base)
.\infra\kubeflow\deploy_kubeflow.ps1 -Kubeconfig $HOME\.kube\config -Environment staging -UiUrl https://kubeflow.example.com
```

Notes:
- The scripts are intentionally conservative: they vendor manifests into `infra/kubeflow/vendor/manifests` and expect you to choose a specific kustomize directory from that tree to apply. Kubeflow manifests are large and multi-component; picking the exact kustomize path depends on the version and installation flavor you need (profiles, istio, cert-manager, etc.).
- For air-gapped installs, vendor the manifests offline or provide a local mirror and edit `vendor_manifests.ps1` accordingly.
