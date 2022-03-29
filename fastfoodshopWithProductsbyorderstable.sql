DROP TABLE IF EXISTS productsbyorders;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;

create table products (id serial primary key, name varchar (50) not null, price varchar (50) not null);
 insert into products (name, price) values ('hamburger','10'),('cocacola','2');
 
create table customers (id serial primary key, name varchar (50) not null);
insert into customers (name) values ('kenneth');

create table orders (id serial primary key, customer_id int, foreign key (customer_id) references customers(id));
insert into orders  (customer_id) values (1);

create table productsbyorders (order_id int, product_id int, foreign key (order_id) references orders(id), foreign key (product_id) references products(id));
insert into productsbyorders (order_id, product_id) values (1,1),(1,2);
