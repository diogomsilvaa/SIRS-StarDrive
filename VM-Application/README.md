**Machine:** Application
**OS:** Debian
**Description:**

## Configure
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

## Procedure

### JDK 17

Run this commands to install the JDK17

```
sudo apt install openjdk-17-jdk
```

Set JAVA_HOME at ~/.bashrc:

```
export JAVA_HOME=/opt/jdk-17
export PATH=$JAVA_HOME/bin:$PATH
```

### Maven 3.8.6

Run this commands to install Maven

```
wget https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz
tar -xvf apache-maven-3.8.6-bin.tar.gz
mv apache-maven-3.8.6 /opt/
```

Set M2_HOME at ~/.bashrc:

```
M2_HOME='/opt/apache-maven-3.8.6'
export "$M2_HOME/bin:$PATH"
```

### MongoDB

Run this commands to install and initialize mongodb

```
sudo apt install mongodb
sudo service mongodb start
```

Enter mongo shell and init a database in port 27017

```
mongo
> use StartDriveDB
```

### Set up TLS Key

```
keytool -genkeypair -alias baeldung -keyalg RSA -keysize 4096 \
  -validity 3650 -dname "CN=localhost" -keypass changeit -keystore keystore.p12 \
  -storeType PKCS12 -storepass changeit
```
