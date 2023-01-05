sudo apt update
sudo apt upgrade
sudo apt autoremove

cat toBash.txt >> ~/.bashrc

sudo apt install openjdk-17-jdk
# Fazer as alterações no bashrc
# export JAVA_HOME=/lib/jvm/java-17-openjdk-amd64
# export PATH=$JAVA_HOME/bin:$PATH


wget https://dlcdn.apache.org/maven/maven-3/3.8.7/binaries/apache-maven-3.8.7-bin.tar.gz
tar -xvf apache-maven-3.8.7-bin.tar.gz
mv apache-maven-3.8.7 /opt/

# Fazer as alterações no bashrc
# export M2_HOME=/opt/apache-maven-3.8.7
# export PATH=$M2_HOME/bin:$PATH

wget -qO - https://www.mongodb.org/static/pgp/server-6.0.asc | sudo apt-key add -
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/6.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-6.0.list
sudo apt-get update
sudo apt-get install -y mongodb-org

keytool -genkeypair -alias baeldung -keyalg RSA -keysize 4096 \
  -validity 3650 -dname "CN=localhost" -keypass changeit -keystore keystore.p12 \
  -storeType PKCS12 -storepass changeit

# RESTART
# sudo systemctl start mongodb
