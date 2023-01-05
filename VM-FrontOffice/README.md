**Machine:** FrontOffice
**OS:** Ubuntu Server
**Description:**

## Configure
In ORACLE go to setting->network and then:
1. In adapter 1 make sure it´s attached to internal network with the name sw-1 and generate new MAC address

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

## Procedure
Run this commands to install the Nginx
```
sudo apt update
sudo apt install nginx
```

Copy the HTML pages to `/var/www/html/`

Copy the virtual host to `/etc/nginx/sites-enabled/`