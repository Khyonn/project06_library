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
- model : OK
- consumer : OK
- business : OK
- webservice : OK
- webapp : TODO
- batch : TODO
- script SQL : OK

### Tests SOAP UI
1) Démarrez l'application webservice en local sur le port 8080 et vérifiez que le service fonctionne en accédant à la page `http://localhost:8080/Library_webservice/services`
2) Placez vous dans le dossier resources/sql
    > $ cd resources/sql
3) Lancez le script afin d'avoir le jeu de données minimal pour les tests
    > $ ./run_sql.sh
4) Importer le projet SOAP UI (`webservice/src/main/resources/soap/Library-soapui-project.xml`)
5) Double-clic sur le projet `Library`
6) Onglet `Test Suites`
7) Clic `bouton play`
