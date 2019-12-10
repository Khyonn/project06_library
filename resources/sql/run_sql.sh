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
echo "insertion du delta TK1"
sudo mysql -u root < 06_delta_tk1.sql
echo "insertion du delta TK2"
sudo mysql -u root < 07_delta_tk2.sql