**Machine:** RouterFront

**OS:** Ubuntu 22

**Description:** 

### Setup Network

In ORACLE go to setting->network and then:
1. In adapter 1 make sure it´s attached to internal network with the name clients and generate new MAC address, make sure promiscuous mode is allow all

Copy 01-network-manager-all.yaml to /etc/netplan/00-installer-config.yaml

Then run 
```
sudo netplan try
```

Press Enter to confirm

Then run
```
sudo netplan apply
```

Run this command to set the router as the default gateway
```
sudo ip route add default via 10.0.4.254
```