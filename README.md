# WebBankApp
DevRep - JEE Project 

Installation :
Intellij
Mysql Workbench : sudo apt install mysql-workbench
Mysql Community Server

Lancer MySQL : service mysql start

Création de la table users :
CREATE TABLE users ( id INT PRIMARY KEY NOT NULL auto_increment, nom VARCHAR(100), prenom VARCHAR(100),email VARCHAR(255));

Ajout d'un utilisateur :
INSERT INTO users (nom,prenom,email) VALUES ('ouersighni','haitham','haitham.o@outlook.com');

L'id n'est pas à renseigner, il s'auto incrémente.
