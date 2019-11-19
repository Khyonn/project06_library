sudo cp ../../webapp/target/Library\ webapp.war $CATALINA_HOME2/webapps/Library_webapp.war
cd $CATALINA_HOME2/bin
echo "Running webapp"
sudo ./startup.sh