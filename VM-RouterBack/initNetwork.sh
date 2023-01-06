sudo mv conf/01-network-manager-all.yaml /etc/netplan/01-network-manager-all.yaml
sudo netplan try
sudo netplan apply

sudo mv conf/before.rules /etc/ufw/before.rules

sudo ufw enable
sudo ip route add 10.0.4.0/24 via 192.168.1.2 dev enp0s8
sudo ufw default deny INCOMING
sudo ufw default deny OUTGOING
sudo ufw default deny FORWARD