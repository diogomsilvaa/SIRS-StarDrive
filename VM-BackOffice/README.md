**Machine:** BackOffice
**OS:** Ubuntu Server
**Description:**

## Configure
In ORACLE go to setting->network and then:
1. In adapter 1 make sure it´s attached to internal network with the name sw-2 and generate new MAC address

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
sudo ip route add default via 192.168.2.254
```

## Procedure
Run this commands to install the Nginx
```
sudo apt update
sudo apt install nginx
```

Copy the www content to `/var/www/html/back`


Copy the virtual host to `/etc/nginx/sites-enabled/`
Create key in the `/usr/local/etc/nginx/` 
`openssl req -new -newkey rsa:4096 -x509 -sha256 -days 365 -nodes -out host.crt -keyout host.key`

Copy the nginx configs to `/usr/local/etc/nginx/nginx.conf`

Restart Nginx Services `service nginx restart`

