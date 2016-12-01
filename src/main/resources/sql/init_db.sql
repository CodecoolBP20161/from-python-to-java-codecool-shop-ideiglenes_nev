DROP TABLE IF EXISTS shop;

CREATE TABLE product
(
id varchar(36) PRIMARY KEY,
Name varchar(40),
Price varchar(40),
Currency varchar(5),
Category varchar(20)REFERENCES categories(Name),
Supplier varchar(20)REFERENCES supplier(Name)
);

CREATE TABLE categories
(
id varchar(40),
Name varchar(40) PRIMARY KEY,
Department varchar(40),
Description varchar(40)
);

CREATE TABLE supplier
(
id varchar(40),
Name varchar(40) PRIMARY KEY,
Department varchar(40),
Description varchar(40)
);