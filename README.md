# project03_library
*Ce projet est à but pédagogique*.

## Enoncé
Le service culturel d’une grande ville souhaite moderniser la gestion de ses bibliothèques. Pour cela, elle désire mettre à disposition de ses usagers, un système de suivi des prêts de leurs ouvrages.  
Le système comprendra :
1) Un site web responsive permettant aux usagers de
    - rechercher des ouvrages et voir le nombres d'exemplaires disponibles
    - suivre les prêts
    - prolonger un prêt
2) Une application mobile Android et iOS fournissant les mêmes services que le site web
3) Une application spécifique pour le personnel des bibliothèque permettant de gérer les emprunts et les livres rendus
4) Un batch qui enverra des mails de relance aux usagers n'ayant pas rendu leurs livres en fin de période de prêt

## Contraintes techniques
Dans une démarche SOA, le coeur de la logique métier sera portée par un module webservice. Les applications s'appuieront sur ce webservice pour fonctionner.  
Le projet sera réalisé en Java EE et avec l'aide du framework Spring. Les livrables seront packagés avec Maven.

## Premier rendu attendu
1) Le web service SOAP connecté à la base de données
2) L’application web connecté au Web service
3) Le batch pour l’envoi de mail de relance connecté au Web service

## Réalisation
L'application est découpé en plusieurs modules Maven :  
- model : le module contient les différentes classes qui seront manipulées
- consumer : le module est en charge de la persistence des données (choix de la persistence : SGBD MySQL)
- business : le module est en charge de la logique métier
- webservice : le module est le point d'entrée aux différents services du projet
    - la plupart des services sont protégés par authentification JWT
- webapp : le module jouera la couche présentation du projet et sera basé sur le webservice
- batch : le module s'occupera des envois de mails de relance pour les prêt non rendus

### Avancement
:white_square_button: module model  
:white_square_button: module consumer  
:white_square_button: module business  
:white_square_button: module webservice  
:white_square_button: module webapp  
:white_square_button: module batch  

:white_square_button: Script SQL  
:white_square_button: Tests SOAP UI  
:black_square_button: Mise à jour des diagrammes

### :warning: Limitations (non demandées mais importantes)

#### Base de données
La base de données n'a pas été prévue pour stocker une énorme quantité d'informations (2147483647 par table => on ne pourra donc pas stocker plus de prêts, plus d'exemplaires de livre). Ce nombre reste raisonnable pour ce qui est des livres et des utilisateurs. Il est possible d'augmenter le nombre d'entrées max en utilisant des clés primaires composées.  

Pour faciliter la démo (cas de tests), les mots de passes sont en clairs !!  
Si vous ré-utilisez ce projet, pensez à utiliser un système de cryptage ou de hashage avant de persister les mots de passes en base lors de la création ou l'édition d'un utilisateur.

#### Webservice
De plus, pensez à utiliser des DTOs ou autres entités ne mettant pas à découvert les informations sensibles (mot de passes) à la récupération de données depuis la couche webservice.

#### Webservice authentification
Il est possible de récupérer un jeton d'authentification utile aux appels webservices 'sensibles'.  
Il a l'avantage de ne pas avoir à faire appel à un serveur d'authentification mais présente cependant le défaut de ne pas être à jour avec des informations de la base de données. Il faudra donc être vigilant vis à vis de l'utilisation de ces tokens.

#### Webapp authentification
L'application web a été conçue de telle manière à enregistrer les informations de connexion et le jeton d'authentification en session (les informations stockées en session sont nettoyées à la fermeture du navigateur ou à la déconnexion de l'utilisateur).  
A la récupération de données personnelles de l'utilisateur, ces informations de connexion permettent de recharger le jeton si ce dernier a expiré. Il arrivera donc probablement un moment où le jeton d'authentification sera expiré, que l'application web tentera d'en récupérer un nouveau mais que les informations de connexion ne seront plus valables (modification depuis un autre service connecté à la BDD ou au webservice des informations du compte utilisateur). Ce cas vide la session, l'utilisateur devra re-saisir manuellement ses informations de connexion.

### Tests SOAP UI
1) Démarrez l'application webservice en local sur le port 8080 et vérifiez que le service fonctionne en accédant à la page `http://localhost:8080/Library_webservice/services`
2) Placez vous dans le dossier resources/sql
    > $ cd resources/sql
3) Lancez le script afin d'avoir le jeu de données minimal pour les tests
    > $ ./run_sql.sh
4) Importer le projet SOAP UI (`webservice/src/main/resources/soap/Library-soapui-project.xml`)
5) Double-clic sur le projet `Library`
6) Onglet `Test Suites`
7) Clic :arrow_forward:

### Configuration Batch
1) Lancer mvn package sur le dossier batch
2) Récupérer le zip `target/batch-1.0-SNAPSHOT-archive-deploy.zip` et le dézipper
3) Modifier les fichiers de configurations (dossier `conf`) :
    - `email.properties` permet de renseigner les informations pour envoyer des emails (serveur SMTP (et port), email, password)
    - `webservice.properties` permet de renseigner les informations liés au webservice (*base URL du webservice* (garder `http://localhost:8080/Library_webservice/services` si en local), email, password)
4) Lancer le batch (dossier `bin`)
    > $ ./run.sh

Les utilisateurs n'ayant pas rendu les livres prêtés recevront un email (voir dossier SPAM)
