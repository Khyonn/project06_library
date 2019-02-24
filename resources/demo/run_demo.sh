cd ../sql
./run_sql_demo.sh
cd ../../
echo "Packaging ..."
mvn package
echo "Deploiement ..."
sudo mv webservice/target/Library\ webservice.war $CATALINA_HOME/webapps/Library_webservice.war
sudo mv webapp/target/Library\ webapp.war $CATALINA_HOME2/webapps/Library_webapp.war
cd $CATALINA_HOME/bin
echo "Running webservice"
sudo ./startup.sh
cd $CATALINA_HOME2/bin
echo "Running webapp"
sudo ./startup.sh