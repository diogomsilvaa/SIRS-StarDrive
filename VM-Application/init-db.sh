if [ $(dpkg-query -W -f='${Status}' mongodb 2>/dev/null | grep -c "ok installed") -eq 0 ];
then
    sudo apt install mongodb;
fi