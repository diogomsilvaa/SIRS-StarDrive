**Machine:** RouterBack
**OS:** Debian
**Description:** 

To initialize this router:
Create a machine with ubuntu server.

In ORACLE go to setting->network and then:
1. In adapter 1 make sure it´s attached to NAT and generate new MAC address
2. In adapter 2 make sure it´s attached to internal network with the name sw-1, generate new MAC address and set promiscuous mode to allow all.
3. In adapter 3 make sure it´s attached to internal network with the name sw-2, generate new MAC address and set promiscuous mode to allow all.

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
Run the followinf command to be able to respond to clients
```
sudo ip route add 10.0.4.0/24 via 192.168.1.2 dev enp0s8
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
