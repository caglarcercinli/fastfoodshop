DROP TABLE orders;
DROP TABLE products;
DROP TABLE customers;

create table products (id serial primary key, name varchar (50) not null, price varchar (50) not null);
 insert into products (name, price) values ('hamburger','10');
 
create table customers (id serial primary key, name varchar (50) not null);
insert into customers (name) values ('kenneth');

create table orders (id serial primary key, product_id int, customer_id int, foreign key (product_id) references products(id), foreign key (customer_id) references customers(id));
insert into orders (product_id, customer_id) values (1,1);
