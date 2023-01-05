**Machine:** Authentication
**OS:** Ubuntu 20
**Description:**

## Configure

ISO: ubuntu-20.04.5-live-server-amd64
Cores: 2
RAM: 2GB
Size: 10GB

Use all the default settings.

Name: José
ServerName: stardriveauth
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

### Set up TLS Key

```
keytool -genkeypair -alias baeldung -keyalg RSA -keysize 4096 \
  -validity 3650 -dname "CN=localhost" -keypass changeit -keystore keystore.p12 \
  -storeType PKCS12 -storepass changeit
```

change on application.properties tha path to the key