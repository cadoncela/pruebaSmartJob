/*USUARIOS*/
INSERT INTO usuarios(id,name, password, created, modified, last_login, email, token, is_active) VALUES ('3057a5db-3fbe-4b4b-a14b-164a56c92870', 'Ragnar', '$2a$10$09KsLZLj2uhsg6LI.6kuYepS6Wn4qlvVEZqKYWc43WhNxWldW2Ksu', NOW(), NOW(), NOW(), 'oli@correro.com', 'TOKEN1', TRUE);

/*PHONES*/
INSERT INTO phones(number, city_code, country_code, usuario_id)VALUES('111', '111', '111', '3057a5db-3fbe-4b4b-a14b-164a56c92870');
INSERT INTO phones(number, city_code, country_code, usuario_id)VALUES('211', '211', '211', '3057a5db-3fbe-4b4b-a14b-164a56c92870');