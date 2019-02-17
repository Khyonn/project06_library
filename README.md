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

## Choix de la stack persistence
Par préférence, le choix s'est posé sur MySQL.

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
:white_square_button: Mise à jour des diagrammes  
:white_square_button: Mise à jour description déploiement

### Conception
Vous trouverez tous les schémas de conceptions dans le dossier `resources/diagrams/`

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

### Optimisations à réaliser
L'authentification rajoutée en cours de développement a induit des modifications de la couche webservice. Il s'avère que dans la plupart des cas, il est préférable effectivement de donner cette responsabilité (vérification de la validité du token) à cette couche qui fait office de point d'entrée. Il y a cependant quelques cas où c'est à la couche business qu'aurait dû revenir ce rôle (exemples: vérification qu'un utilisateur qui modifie un profil soit un administrateur ou l'utilisateur lui-même / vérification que ce soit un administrateur qui ajoute un livre, un exemplaire, un prêt) car ces demandes-là font partie du besoin métier exprimé et que la couche webservice n'est qu'un point d'entré technique qui se doit d'être sécurisé. Si une nouvelle interface devait être créée comme point d'entrée, et qu'elle se basait sur le module business pour fonctionner, alors il faudrait que cette interface n'ait pas à ré-implémenter les vérifications que la couche webservice propose actuellement.  
L'autre optimisation aurait été de correctement penser les ErrorCode. En effet, le choix utilisé fut d'intégrer en un seul enum les différents codes d'erreurs. Or, on constate pourtant que certains codes sont préfixés de "LBE" pour LibraryBusinessException et d'autres de "LTE" pour LibraryTechnicalException. Il est effectivement idiot d'avoir créé deux classes (+ classe exception parent) pour gérer les exceptions et pas deux enums différents n'ayant cette fois-ci non pas une chaîne de caractère comme attribut mais un nombre entier.
Leur utilisation laisse aussi à désirer. Parfois le choix de savoir s'il s'agit d'un souci technique ou d'un souci métier à lever est ambigüe. Mais la plupart du temps, notamment la levée d'exception sur une taille de chaine en rapport avec les limitations des champs en base, il aurait été logique de lever une exception technique et non métier.  
Enfin concernant le déploiement, il aurait été intéressant d'utiliser le plugin "Apache Tomcat Maven Plugin" afin de simplifier la livraison.

## Déploiement et tests

### Overview
Comme indiqué ci-dessus, le projet est sur une stack Java (ici Java 8), Tomcat, Mysql avec une gestion des package avec Maven et des tests réalisés avec SOAP UI. Comme la webapp dépend du webservice, il faut pouvoir déployer en deux temps : d'abord le webservice puis la webapp. C'est pourquoi nous simuleront le comportement en utilisant deux instances de Tomcat, l'une écoutant sur le port 8080, l'autre sur le port 8081.  
Voici les liens pour obtenir les outils minimums pour fonctionner :  
[JAVA](https://www.oracle.com/technetwork/java/javaee/downloads/index.html)  
[Tomcat](https://tomcat.apache.org/download-90.cgi)  
[Maven](https://maven.apache.org/download.cgi)  
[Mysql](https://www.mysql.com/fr/downloads/)  
[SOAP UI](https://www.soapui.org/downloads/soapui.html)  
  
Le déploiement va se passer en 5 étapes :
- Génération de la BDD avec jeu de données de test et de démo
- Configuration et déploiement du webservice
- Configuration de déploiement de la webapp
- Configuration et indications de lancement du batch
- Configuration et lancement des tests SOAP-UI

Pour commencer, clonez / téléchargez le repo git et placez vous dans le dossier `project03_library` (dézippé si téléchargement en .zip)

A chaque étape, on considérera que vous vous trouvez dans ce dossier parent.  
On considérera que la variable `$CATALINA_HOME` référence le dossier Tomcat principal et que la vairable `$CATALINA_HOME2` référence le dossier Tomcat secondaire. 

#### 1) BDD : Schéma et données
1) Placez vous dans le dossier resources/sql
    > $ cd resources/sql
2) Lancez le script afin d'avoir le jeu de données
    > $ ./run_sql_demo.sh  

Le script permet de charger les différents scripts du dossier sur Mysql (le schéma, les tables, les contraintes, les utilisateurs, les données de tests et les données de démo).

#### 2) Configuration et déploiement du webservice
1) Si votre instance de MySQL ne tourne pas sur le port 3306 (défaut), editez le fichier `webservice/src/main/resources/jdbc.properties` et modifiez la ligne `jdbc.url` pour changer le port.
2) Lancez un maven package depuis le dossier `webservice/`
    > $ mvn package  
3) Déplacez le fichier .war généré dans le dossier `$CATALINA_HOME/webapps/`
    > $ sudo mv webservice/target/Library\ webservice.war $CATALINA_HOME/webapps/Library_webservice.war  
4) Lancez Tomcat
    > $ cd $CATALINA_HOME/bin/  
    > $ sudo ./startup.sh  
5) Vérifiez que le webservice est fonctionnel en naviguant à l'adresse `localhost:8080/Library_webservice/services`

#### 3) Configuration et déploiement de la webapp
1) Le webservice sera déployé sur `http://localhost:8080/Library_webservice/services`. Editez le fichier `webapp/src/main/resources/webservice.properties` et modifiez la ligne webservice.services.url
2) Editer le fichier `$CATALINA_HOME2/conf/server.xml` et changez les ports 8080 par 8081, 8005 par 8006 et 8009 par 8010
3) Lancez un maven package depuis le dossier `webapp/`
    > $ mvn package  
4) Déplacez le fichier .war généré dans le dossier `$CATALINA_HOME2/webapps/`
    > $ sudo mv webapp/target/Library\ webapp.war $CATALINA_HOME2/webapps/Library_webapp.war  
5) Lancez Tomcat
    > $ cd $CATALINA_HOME2/bin/  
    > $ sudo ./startup.sh  
5) Vérifiez que la webapp est fonctionnelle en naviguant à l'adresse `localhost:8081/Library_webapp`

#### 4) Configuration et lancement du Batch
1) Lancer mvn package sur le dossier batch
2) Récupérer le zip `target/batch-1.0-SNAPSHOT-archive-deploy.zip` et le dézipper
3) Modifier les fichiers de configurations (dossier `conf/`) :
    - `email.properties` permet de renseigner les informations pour envoyer des emails (serveur SMTP (et port), email, password)  
        Vous trouverez les informations à inscrire en fonction de l'hebergeur de votre email (exemples: [GMAIL](https://support.google.com/a/answer/176600?hl=fr), [outlook.com](https://support.office.com/fr-fr/article/param%C3%A8tres-pop-imap-et-smtp-pour-outlook-com-d088b986-291d-42b8-9564-9c414e2aa040))
    - `webservice.properties` permet de renseigner les informations liés au webservice (*base URL du webservice* (garder `http://localhost:8080/Library_webservice/services`), email, password)
4) Pour vérifier le bon fonctionnement du batch, vérifiez dans la table library.Loans qu'il y ait bien des prêts en retard (returnDate = null et startDate + durée de prolongation * prolongationNumber > aujourd'hui). Et éditez l'email des utilisateurs concernés pour les recevoir
5) Lancer le batch (dossier `bin/`)
    > $ ./run.sh

#### 5) Configuration Tests SOAP-UI
1) Démarrez SOAP UI et importez le projet depuis (`webservice/src/main/resources/soap/Library-soapui-project.xml`)
2) Récupérez l'adresse du webservice (Si non changée : `http://localhost:8080/Library_webservice/services`) et editez la propriété `servicesUrl` du projet Library
3) Double-clic sur le projet `Library`
4) Onglet `Test Suites`
5) Clic :arrow_forward:
