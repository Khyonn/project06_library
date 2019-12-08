sudo cp ../../webservice/target/Library\ webservice.war $CATALINA_HOME/webapps/Library_webservice.war
cd $CATALINA_HOME/bin
echo "Running webservice"
sudo ./startup.sh