# project06_library
*Ce projet est à but pédagogique*.

## Dépendances
Le projet est sur une stack Java (ici Java 8), Tomcat, Mysql avec une gestion des package avec Maven et des tests réalisés avec SOAP UI. Comme la webapp dépend du webservice, il faut pouvoir déployer en deux temps : d'abord le webservice puis la webapp. C'est pourquoi nous simuleront le comportement en utilisant deux instances de Tomcat, l'une écoutant sur le port 8080, l'autre sur le port 8081.  
Voici les liens pour obtenir les outils minimums pour fonctionner :  
[JAVA](https://www.oracle.com/technetwork/java/javaee/downloads/index.html)  
[Tomcat](https://tomcat.apache.org/download-90.cgi)  
[Maven](https://maven.apache.org/download.cgi)  
[Mysql](https://www.mysql.com/fr/downloads/)  
[SOAP UI](https://www.soapui.org/downloads/soapui.html)  
  
## Déploiement
Le déploiement va se passer en 3 étapes :  
- Editer les fichiers de configuration du webservice, de la webapp, et du batch d'avertissement
- Lancer le déploiement du webservice et de la webapp via le fichier `./resources/demo/run_demo.sh`
- Lancer le packaging du batch

### 1) Configuration
#### 1a) Webservice
Placez-vous dans le dossier `webservice/src/main/resources/`
Le dossier contient :  
- Le fichier `business.properties` (contient les constantes des règles métiers)  
- Le fichier `jdbc.properties` (contient les informations pour la connexion à la BDD)  
#### 1b) Webapp
Placez-vous dans le dossier `webapp/src/main/resources/`
Le dossier contient :    
- Le fichier `webservice.properties` (contient l'adresse du webservice => par défaut `localhost:8080/Library_webservice/services`)
#### 1c) Batch
Placez-vous dans le dossier `loan-peremption-warn-batch/src/main/resources/`
Le dossier contient :
- Le fichier `webservice.properties` (contient l'adresse du webservice => par défaut `localhost:8080/Library_webservice/services`)
- Le fichier `email.properties` (!! à éditer !! contient les infos sur l'adresse mail depuis laquelle sont envoyés les emails d'avertissement et le service smtp qui le permet)  
### 2) Déploiement webservice et webapp
- Placez vous dans le dossier `resources/demo`
    > $ ./run_demo.sh  
### 3) Packaging batch
- Placez vous dans le dossier `loan-peremption-warn-batch`  
    > $ ./mvn clean compile package   
    
## Utilisation
### 1) Webservice et SOAP UI
Si le déploiement s'est correctement passé, vous pourrez constater à l'adresse localhost:8080/Library_webservice/services  
Vous pourrez alors procéder aux tests SOAP UI [voir plus bas](#tests-soap-ui)
### 2) Webapp
Si le déploiement s'est correctement passé, vous pourrez profiter de la webapp à l'adresse localhost:8081/Library_webapp/
### 3) Batch
Si le packaging s'est correctement passé, vous trouverez dans le dossier `loan-peremption-warn-batch/target/` un fichier `Library loan peremption warn batch.jar` que vous pourrez lancer via la commande :  
    > $ java -jar "./Library loan peremption warn batch.jar"

## Tests SOAP UI
1) Démarrez SOAP UI et importez le projet depuis (`webservice/src/main/resources/soap/Library-soapui-project.xml`)
2) Récupérez l'adresse du webservice (Si non changée : `http://localhost:8080/Library_webservice/services`) et editez la propriété `servicesUrl` du projet Library si besoin
3) Double-clic sur le projet `Library`
4) Onglet `Test Suites`
5) Clic :arrow_forward:
