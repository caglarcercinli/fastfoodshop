INSERT INTO customers (name)
VALUES ('test');
INSERT INTO ORDERS (id, customer_id)
VALUES (2, (select id from customers where name='test'));