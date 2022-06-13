INSERT INTO users (name, password, enabled)
VALUES ('test', 'test', true);
INSERT INTO ORDERS (user_id)
VALUES ((select id from users where name='test'));