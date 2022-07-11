# Event Carried State Transfer

Link: https://www.vinsguru.com/event-carried-state-transfer/

<img width="520" alt="Screenshot 2022-07-11 at 5 54 46 PM" src="https://user-images.githubusercontent.com/54174687/178263620-53247f10-7a8b-4cdb-bfe6-833c37901839.png">


```sql
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
```

MongoDB holds record:

```sh
{
    "_id" : ObjectId("60a536412306ab5bdd7a6b06"),
    "user" : {
        "_id" : NumberLong(1),
        "firstname" : "John",
        "lastname" : "Doe",
        "email" : "john-doe@gmail.com"
    },
    "product" : {
        "_id" : NumberLong(0),
        "description" : "ipad"
    },
    "price" : 300.0,
    "_class" : "com.example.entity.PurchaseOrder"
}
```

Run the Program

```sh
curl --location --request PUT 'http://localhost:8080/user-service/update' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id" : 1,
    "firstname" : "John",
    "lastname" : "Doe",
    "email" : "john-doe-updated@hotmail.com"
}'
```

After I hit the endpoint 

```sh
/* 1 */
{
    "_id" : ObjectId("60a536412306ab5bdd7a6b06"),
    "user" : {
        "_id" : NumberLong(1),
        "firstname" : "John",
        "lastname" : "Doe",
        "email" : "john-doe-updated@hotmail.com"
    },
    "product" : {
        "_id" : NumberLong(0),
        "description" : "ipad"
    },
    "price" : 300.0,
    "_class" : "com.example.entity.PurchaseOrder"
}
```
<img width="1206" alt="Screenshot 2022-06-29 at 10 37 22 AM" src="https://user-images.githubusercontent.com/54174687/176356156-4e646abf-6a0f-4366-89f6-09b542d424d5.png">


