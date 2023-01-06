**Machine:** FrontOffice
**OS:** Ubuntu Server
**Description:**

## Procedure
Run this commands to install the Nginx
```
sudo apt update
sudo apt install nginx
```

Copy the www folder content to `/var/www/html/back`


Copy the nginx configs to `/etc/nginx/`

Create key in the `/usr/local/etc/nginx/` 
`openssl req -new -newkey rsa:4096 -x509 -sha256 -days 365 -nodes -out host.crt -keyout host.key`


Restart Nginx Services `service nginx restart`


## Configure
In ORACLE go to setting->network and then:
1. In adapter 1 make sure it´s attached to internal network with the name sw-1 and generate new MAC address and set to allow all on promiscuous mode
2. In adapter 2 make sure it´s attached to internal network with the name front and generate new MAC address and set to allow all on promiscuous mode

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
sudo ip route add default via 192.168.1.254
```
Run to enable forwarding
```
sudo sysctl net.ipv4.ip_forward=1
```
Confirm that the flag value was updated to 1:
```
/sbin/sysctl net.ipv4.conf.all.forwarding
```
Run to enable ufw
```
sudo ufw enable
```
Run the following command to make connections from enp0s3 to enp0s8
```
sudo ip route add 10.0.4.0/24 via 10.0.1.254 dev enp0s8
```
Run the following command to see the default rules of ufw
```
sudo ufw status verbose
```
If incoming is denied run
```
sudo ufw default allow INCOMING
```
If outgoing is denied run
```
sudo ufw default allow OUTGOING
```
If routed is denied run
```
sudo ufw default allow FORWARD
```
