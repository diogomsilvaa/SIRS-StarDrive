**Machine:** RouterFront

**OS:** Debian

**Description:** To initialize this router:
Create a machine with ubuntu server.

In ORACLE go to setting->network and then:
1. In adapter 1 make sure it´s attached to internal network with the name clients, generate new MAC address and set promiscuous mode to allow all
2. In adapter 2 make sure it´s attached to internal network with the name front, generate new MAC address and set promiscuous mode to allow all
3. In adapter 3 make sure it´s attached to internal network with the name auth, generate new MAC address and set promiscuous mode to allow all

Copy the file 01-network-manager-all.yaml to /etc/netplan/00-installer-config.yaml

Run 
```
sudo netplan try
```
Press Enter to confirm

Run
```
sudo netplan apply
```
Run to enable forwarding
```
sudo sysctl net.ipv4.ip_forward=1
```
Copy the file before.rules to /etc/ufw/before.rules

Run to enable ufw
```
sudo ufw enable
```
Run this command to set the FrontOffice as the default gateway
```
sudo ip route add default via 10.0.1.2
```

Run the following command to see the default rules of ufw
```
sudo ufw status verbose
```
If incoming is allowed run
```
sudo ufw default deny INCOMING
```
If outgoing is allowed run
```
sudo ufw default deny OUTGOING
```
If routed is allowed run
```
sudo ufw default deny FORWARD
```
