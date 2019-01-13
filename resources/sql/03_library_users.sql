USE library;

DROP USER IF EXISTS 'library_webservice'@'%';

CREATE USER 'library_webservice'@'%' IDENTIFIED BY 'lws1234';
GRANT ALL ON library.* to 'library_webservice'@'%';