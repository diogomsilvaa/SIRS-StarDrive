**Machine:** RouterFront

**OS:** Debian

**Description:** To initialize this router:

1. Create a machine with ubuntu server.
2. Copy the file 01-network-manager-all.yaml to /etc/netplan/00-installer-config.yaml
3. run `netplan try`
4. run `netplan apply`
5. Copy the file before.rules to /etc/ufw/before.rules
6. run `sudo ufw enable`