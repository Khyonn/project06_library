cd ../sql
./run_sql_demo.sh
cd ../../
echo "Packaging ..."
mvn clean compile package
echo "Deploiement ..."
cd resources/demo
./ws_deploy.sh
./webapp_deploy.sh