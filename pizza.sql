DROP TABLE pizzacommandegarniture;
DROP TABLE pizzacommande;
DROP TABLE commande;
DROP TABLE contient;
DROP TABLE pizza;
DROP TABLE garniture;
DROP TABLE client;
DROP TABLE SEQUENCES;

CREATE TABLE client (
	cliId INTEGER PRIMARY KEY,
	cliNom VARCHAR(20) NOT NULL,
	cliEmail VARCHAR(50) NOT NULL,
	cliAdresse VARCHAR(150) NOT NULL
);

ALTER TABLE client ADD CONSTRAINT u_email unique (cliEmail);

CREATE TABLE pizza (
	pId INTEGER PRIMARY KEY,
	pNom VARCHAR(50) NOT NULL
);

ALTER TABLE pizza ADD CONSTRAINT u_nomPizza unique (pNom);

CREATE TABLE garniture (
	gId INTEGER PRIMARY KEY ,
	gNom VARCHAR(30) NOT NULL,
	gPrix FLOAT NOT NULL
);

ALTER TABLE garniture ADD CONSTRAINT u_nomGarniture unique (gNom);

CREATE TABLE contient (
	cPizza INTEGER NOT NULL,
	cGar INTEGER NOT NULL
);

ALTER TABLE contient ADD CONSTRAINT 
	pk_contient primary key(cPizza,cGar);

ALTER TABLE contient ADD CONSTRAINT 
	fk_contient_pizza foreign key(cPizza) references pizza;

ALTER TABLE contient ADD CONSTRAINT 
	fk_contient_gar foreign key(cGar) references garniture;


CREATE TABLE commande (
        comId INTEGER PRIMARY KEY,
	comDate DATE,
	comClient INTEGER NOT NULL,
	comEnAttente CHAR NOT NULL
);

ALTER TABLE commande ADD CONSTRAINT 
	fk_commande_client foreign key(comClient) references client; 

CREATE TABLE pizzacommande (
	pcNumCom INTEGER NOT NULL,
	pcNum INTEGER NOT NULL,
	pcNom VARCHAR(50) NOT NULL,
        pcPrix FLOAT NOT NULL,
        pcQuant INTEGER NOT NULL
);

ALTER TABLE pizzacommande ADD CONSTRAINT 
	pk_pizzacommande primary key(pcNumCom, pcNum);

ALTER TABLE pizzacommande ADD CONSTRAINT 
	fk_pizzacommande_commande foreign key(pcNumCom) references commande; 


CREATE TABLE pizzacommandegarniture (
	pcgNumCom INTEGER NOT NULL,
	pcgNum INTEGER NOT NULL,
	pcgGar INTEGER NOT NULL
);

ALTER TABLE pizzacommandegarniture ADD CONSTRAINT 
	pk_pizzacommandegarniture primary key(pcgNumCom, pcgNum, pcgGar);

ALTER TABLE pizzacommandegarniture ADD CONSTRAINT 
	fk_pizcomgar_pizzacommande foreign key(pcgNumCom, pcgNum) references pizzacommande(pcNumCom, pcNum); 

ALTER TABLE pizzacommandegarniture ADD CONSTRAINT 
	fk_pizcomgar_garniture foreign key(pcgGar) references garniture;

INSERT INTO pizza VALUES (1,'Margherita');
INSERT INTO pizza VALUES (2,'Hawai');
INSERT INTO pizza VALUES (3,'Quatre saisons');
INSERT INTO pizza VALUES (4,'Champignons');
INSERT INTO pizza VALUES (5,'Chorizo');
INSERT INTO pizza VALUES (6,'Quatre fromages');
INSERT INTO pizza VALUES (7,'Veggie');
INSERT INTO pizza VALUES (8,'Que tomate');
INSERT INTO pizza VALUES (9,'Blanche');

INSERT INTO garniture VALUES (1,'tomate', 1);
INSERT INTO garniture VALUES (2,'olives', 1.5);
INSERT INTO garniture VALUES (3,'mozzarella', 1);
INSERT INTO garniture VALUES (4,'ananas', 1);
INSERT INTO garniture VALUES (5,'jambon', 2);
INSERT INTO garniture VALUES (6,'champignons', 1.5);
INSERT INTO garniture VALUES (7,'mais', 1);
INSERT INTO garniture VALUES (8,'chorizo', 2);
INSERT INTO garniture VALUES (9,'paprika', 1);
INSERT INTO garniture VALUES (10,'emmental', 1.5);
INSERT INTO garniture VALUES (11,'parmesan', 1);
INSERT INTO garniture VALUES (12,'gorgonzola', 1.5);
INSERT INTO garniture VALUES (13,'fromage aux herbes', 1.5);
INSERT INTO garniture VALUES (14,'tomates cerises', 1);
INSERT INTO garniture VALUES (15,'courgettes', 1.5);
INSERT INTO garniture VALUES (16,'fromage', 1.5);
INSERT INTO garniture VALUES (17,'artichaut', 1.5);

INSERT INTO contient VALUES (1,1);
INSERT INTO contient VALUES (1,3);
INSERT INTO contient VALUES (1,16);
INSERT INTO contient VALUES (2,1);
INSERT INTO contient VALUES (2,4);
INSERT INTO contient VALUES (2,5);
INSERT INTO contient VALUES (2,6);
INSERT INTO contient VALUES (2,3);
INSERT INTO contient VALUES (2,7);
INSERT INTO contient VALUES (3,1);
INSERT INTO contient VALUES (3,5);
INSERT INTO contient VALUES (3,2);
INSERT INTO contient VALUES (3,6);
INSERT INTO contient VALUES (3,9);
INSERT INTO contient VALUES (3,17);
INSERT INTO contient VALUES (3,16);
INSERT INTO contient VALUES (4,1);
INSERT INTO contient VALUES (4,6);
INSERT INTO contient VALUES (4,2);
INSERT INTO contient VALUES (4,16);
INSERT INTO contient VALUES (5,1);
INSERT INTO contient VALUES (5,16);
INSERT INTO contient VALUES (5,8);
INSERT INTO contient VALUES (5,2);
INSERT INTO contient VALUES (6,3);
INSERT INTO contient VALUES (6,10);
INSERT INTO contient VALUES (6,11);
INSERT INTO contient VALUES (6,12);
INSERT INTO contient VALUES (7,1);
INSERT INTO contient VALUES (7,16);
INSERT INTO contient VALUES (7,14);
INSERT INTO contient VALUES (7,9);
INSERT INTO contient VALUES (7,15);
INSERT INTO contient VALUES (7,6);
INSERT INTO contient VALUES (7,2);
INSERT INTO contient VALUES (8,1);

CREATE table SEQUENCES (
     id varchar(50) not null,
     valeur numeric(10) not null,
     constraint IDSEQUENCE primary key (id));

Insert Into SEQUENCES values('Client', 0);
Insert Into SEQUENCES values('Commande', 0);
