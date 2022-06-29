CREATE TABLE users(
   id serial PRIMARY KEY,
   firstname VARCHAR (50),
   lastname VARCHAR (50),
   email varchar(50)
);

CREATE TABLE product(
   id serial PRIMARY KEY,
   description VARCHAR (500),
   price numeric (10,2) NOT NULL,
   qty_available integer NOT NULL
);

CREATE TABLE purchase_order(
    id serial PRIMARY KEY,
    user_id integer references users (id),
    product_id integer references product (id),
    price numeric (10,2) NOT NULL
);


INSERT INTO public.users
(id, firstname, lastname, email)
VALUES(1, 'John', 'Doe', 'john.doe@gmail.com');
INSERT INTO public.users
(id, firstname, lastname, email)
VALUES(2, 'Neha', 'Parate', 'neha.parate@gmail.com');
INSERT INTO public.users
(id, firstname, lastname, email)
VALUES(3, 'Shrutika', 'Dekate', 'shrutika.dekate@gmail.com');



INSERT INTO public.product (id,description,price,qty_available)
	VALUES (1,'ipad',300,10);
INSERT INTO public.product (id,description,price,qty_available)
	VALUES (2,'iphone',650,98);
INSERT INTO public.product (id,description,price,qty_available)
	VALUES (3,'tv',320,560);
	
	
	
-- Auto-generated SQL script #202206281842
INSERT INTO public.purchase_order (id,user_id,product_id,price)
	VALUES (1,1,1,300);
INSERT INTO public.purchase_order (id,user_id,product_id,price)
	VALUES (2,2,1,250);
INSERT INTO public.purchase_order (id,user_id,product_id,price)
	VALUES (3,2,2,650);
INSERT INTO public.purchase_order (id,user_id,product_id,price)
	VALUES (4,3,3,320);


