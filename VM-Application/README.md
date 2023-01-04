**Machine:** Application
**OS:** Debian
**Description:**

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
