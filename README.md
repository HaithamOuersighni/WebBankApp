# WebBankApp
DevRep - JEE Project 

Installation :
Intellij
Mysql Workbench : sudo apt install mysql-workbench
Mysql Community Server

Lancer MySQL : 
service mysql start
mysql -u root -p bankdb
mdp : root
Création de la table users :


CREATE TABLE users ( id INT PRIMARY KEY NOT NULL auto_increment=1001, nom VARCHAR(100) not null, prenom VARCHAR(100) not null,email VARCHAR(255) not null, password VARCHAR(255) not null, admin boolean not null default 0);

alter table users add constraint mail_unique UNIQUE (email);
  
CREATE TABLE accounts ( id INT PRIMARY KEY NOT NULL auto_increment, idUser INT foreign key (idUser) references users(id), value double, decouvert int not null default 0, depot int not null default 100, retrait int not null default 100);
    
Ajout d'un utilisateur :
INSERT INTO users (nom,prenom,email) VALUES ('ouersighni','haitham','haitham.o@outlook.com');

L'id n'est pas à renseigner, il s'auto incrémente.





front connection :

connecter : laisser span logout et connected
non connecter : laisser span connection avec les differents moyen et span not connected

(jouer avec les display none)
