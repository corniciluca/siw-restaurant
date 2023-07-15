INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'Mezze maniche “Il Cappelli” Monograno Felicetti con guanciale croccante marinato al pepe nero, uova e Pecorino romano DOP','PRIMO','/images_uploaded/dishes/PRIMO/p2.png','Mezze maniche alla carbonara',12);
INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'Spaghetti “Il Cappelli” Monograno Felicetti al pomodoro e Parmigiano Reggiano con burrata pugliese e basilico fresco','PRIMO','/images_uploaded/dishes/PRIMO/p3.png','Spaghetti pomodoro & burrata',11);
INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'Spaghetti “Il Cappelli” Monograno Felicetti all’aglio, olio e peperoncino con tartare di gambero rosso e scorzetta di limone','PRIMO','/images_uploaded/dishes/PRIMO/p4.png','Spaghetti aglio, olio e peperoncino con tartare di Gambero Rosso',15);
INSERT INTO dishes(id,description,dish_type,image,name,price)  values(nextval('dishes_seq'),'Tagliatelle all’uovo “Pietro Massi” con ragù e Parmigiano Reggiano','PRIMO','/images_uploaded/dishes/PRIMO/p1.png','Tagliatelle al ragù bolognese',12);
INSERT INTO dishes(id,description,dish_type,image,name,price)  values(nextval('dishes_seq'),'Trofie con pesto di basilico Genovese DOP, patate e fagiolini','PRIMO','/images_uploaded/dishes/PRIMO/p5.png','Trofie al pesto',12);

INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'','CONTORNO','/images_uploaded/dishes/CONTORNO/p1.png','Insalata mista',5);
INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'','CONTORNO','/images_uploaded/dishes/CONTORNO/p2.png','Patate novelle al forno',5);
INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'','CONTORNO','/images_uploaded/dishes/CONTORNO/p3.png','Verdure grigliate',5);

INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'Crema tiramisù con savoiardo, caffè e cacao','DOLCE','/images_uploaded/dishes/DOLCE/p1.png','Tiramisù',6);
INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'Crema al formaggio e vaniglia su base croccante con salsa ai frutti di bosco','DOLCE','/images_uploaded/dishes/DOLCE/p2.png','Cheesecake',6);
INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'Dischetti di pasta sfoglia caramellata con crema Chantilly','DOLCE','/images_uploaded/dishes/DOLCE/p3.png','Millefoglie Chantilly',6);

INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'','BEVANDA','/images_uploaded/dishes/BEVANDA/p1.jpg','San Bernardo Acqua 50 cl.',2);
INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'','BEVANDA','/images_uploaded/dishes/BEVANDA/p2.png','San Bernardo 75 cl.',3);

INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'Filetto di manzo alla griglia, servito con patate al forno','SECONDO','/images_uploaded/dishes/SECONDO/p1.png','Classic Fillet.',19);
INSERT INTO dishes(id,description,dish_type,image,name,price) VALUES (nextval('dishes_seq'),'Tagliata di filetto di manzo alla griglia, servito con rucola, patate al forno e salsa Barbecue','SECONDO','/images_uploaded/dishes/SECONDO/p2.png','Classic Fillet versione Mississippi',20.50);




insert into users (id,email,name,role,surname) values(999,'mario@gmail.com','mario','ADMIN','rossi')
insert into users (id,email,name,role,surname) values(998,'marco@gmail.com','marco','CUSTOMER','rossi')
insert into users (id,email,name,role,surname) values(997,'franco@gmail.com','franco','DELIVERYMAN','rossi')
insert into users (id,email,name,role,surname) values(996,'francesco@gmail.com','francesco','DELIVERYMAN','rossi')
insert into deliveryman(num_orders_delivering,id) values(0,997)
insert into deliveryman(num_orders_delivering,id) values(0,996)

insert into customer (address,phone_number,id) values('Vicolo corto ','3382838',998)
insert into admin (id) values(999)
insert into credentials (id,password,username,user_id) values(998,'$2a$10$DseXVaKFAl.dtm9cWiKNoOASEMIuu/.axSVJPx2WCrsjRRNW8dFLG','marco',998)
insert into credentials (id,password,username,user_id) values(999,'$2a$10$DseXVaKFAl.dtm9cWiKNoOASEMIuu/.axSVJPx2WCrsjRRNW8dFLG','rossi',999)
insert into credentials (id,password,username,user_id) values(997,'$2a$10$DseXVaKFAl.dtm9cWiKNoOASEMIuu/.axSVJPx2WCrsjRRNW8dFLG','franco',997)
insert into credentials (id,password,username,user_id) values(996,'$2a$10$DseXVaKFAl.dtm9cWiKNoOASEMIuu/.axSVJPx2WCrsjRRNW8dFLG','francesco',996)
