INSERT INTO customers (name, password, enabled)
VALUES ('test', 'test', true);
INSERT INTO ORDERS (customer_id)
VALUES ((select id from customers where name='test'));