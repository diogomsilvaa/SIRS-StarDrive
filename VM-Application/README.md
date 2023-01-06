**Machine:** Application
**OS:** Ubuntu 20
**Description:**

## Configure

ISO: ubuntu-20.04.5-live-server-amd64
Cores: 2
RAM: 2GB
Size: 20GB

Use all the default settings.

Name: José
ServerName: stardrive
user: jose
pass: taralhoco

Run these 3 Commands:

```
sudo apt update
sudo apt upgrade
sudo apt autoremove
```


### JDK 17

Run this commands to install the JDK17

```
sudo apt install openjdk-17-jdk
```

Set JAVA_HOME at ~/.bashrc:

```
export JAVA_HOME=/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
```

### Maven 3.8.7

Run this commands to install Maven

```
wget https://dlcdn.apache.org/maven/maven-3/3.8.7/binaries/apache-maven-3.8.7-bin.tar.gz
tar -xvf apache-maven-3.8.7-bin.tar.gz
mv apache-maven-3.8.7 /opt/
```

Set M2_HOME at ~/.bashrc:

```
M2_HOME='/opt/apache-maven-3.8.7'
export "$M2_HOME/bin:$PATH"
```

### MongoDB

Run this commands to install and initialize mongodb

```
wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | sudo apt-key add -
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/4.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.4.list
sudo apt-get update
sudo apt-get install mongodb-org=4.4.8 mongodb-org-server=4.4.8 mongodb-org-shell=4.4.8 mongodb-org-mongos=4.4.8 mongodb-org-tools=4.4.
sudo systemctl start mongod
```

### Set up TLS Key

```
keytool -genkeypair -alias baeldung -keyalg RSA -keysize 4096 \
  -validity 3650 -dname "CN=localhost" -keypass changeit -keystore keystore.p12 \
  -storeType PKCS12 -storepass changeit
```

change on application.properties tha path to the key

## Network
In ORACLE go to setting->network and then:
1. In adapter 1 make sure it´s attached to internal network with the name sw-2, generate new MAC address and set promiscuous mode to allow all

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
sudo ip route add default via 192.168.0.254
```


