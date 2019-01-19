echo "creation de la base de donnée"
sudo mysql -u root < 00_library.sql
echo "insertion des tables"
sudo mysql -u root < 01_library_tables.sql
echo "ajout des contraintes"
sudo mysql -u root < 02_library_constraints.sql
echo "ajout des droits"
sudo mysql -u root < 03_library_users.sql
echo "insertion du jeu de données"
sudo mysql -u root < 04_library_data.sql