sudo mv conf/01-network-manager-all.yaml /etc/netplan/01-network-manager-all.yaml
sudo netplan try
sudo netplan apply
sudo ip route add default via 192.168.0.254