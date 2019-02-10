echo "creation du jeu de base"
./run_sql.sh
echo "insertion du jeu de données pour la démo"
sudo mysql -u root < 05_library_demo_data.sql