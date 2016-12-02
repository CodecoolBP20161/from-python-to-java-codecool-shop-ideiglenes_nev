DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS supplier;


CREATE TABLE product_category
(
  id SERIAL PRIMARY KEY,
  name varchar(255),
  department varchar(255),
  description TEXT
);

CREATE TABLE supplier
(
  id SERIAL PRIMARY KEY,
  name varchar(255),
  description TEXT
);

CREATE TABLE product
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  defaultPrice REAL,
  defaultCurrency VARCHAR(255),
  productCategory INTEGER REFERENCES product_category(id),
  supplier INTEGER REFERENCES supplier(id)
);