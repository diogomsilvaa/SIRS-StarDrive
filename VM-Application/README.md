**Machine:** Application
**OS:** Debian
**Description:**

## Procedure

### JDK 19.0.1

Run this two commands to install the JDK19

```
curl -o https://download.oracle.com/java/19/latest/jdk-19_linux-x64_bin.deb
sudo apt-get install jdk-19_linux-x64_bin.deb
```

Set JAVA_HOME at ~/.bashrc:

```
export JAVA_HOME=/usr/lib/jvm/jdk-19
export PATH=$JAVA_HOME/bin:$PATH
```

### Maven 3.8.6

Run this two commands to install Maven

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
