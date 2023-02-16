set foreign_key_checks = 0;

delete from city;
delete from cuisine;
delete from state;
delete from form_payment;
delete from role;
delete from role_permission;
delete from permission;
delete from product;
delete from restaurant;
delete from restaurant_form_payment;
delete from app_user;
delete from user_role;

set foreign_key_checks = 1;

alter table city auto_increment = 1;
alter table cuisine auto_increment = 1;
alter table state auto_increment = 1;
alter table form_payment auto_increment = 1;
alter table role auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table app_user auto_increment = 1;

insert into cuisine(id, name) values(1, 'Indian');
insert into cuisine(id, name) values(2, 'Thai');
insert into cuisine(id, name) values (3, 'Argentina');
insert into cuisine(id, name) values (4, 'Brasileira');

insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into restaurant(name, shipping_fee, cuisine_id, registration_date, update_date, active, open, address_zip, address_street, address_number, address_neighborhood, address_city_id) values('Tuk Tuk Indian Food', 15, 1, utc_timestamp, utc_timestamp, true, true, '009922', 'Rua Joao', '12', 'Centro',1);
insert into restaurant(name, shipping_fee, cuisine_id, registration_date, update_date, active, open) values('Thai Gourmet', 10, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurant(name, shipping_fee, cuisine_id, registration_date, update_date, active, open) values('Thai Delivery', 9.50, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurant(id, name, shipping_fee, cuisine_id, registration_date, update_date, active, open) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true, true);
insert into restaurant(id, name, shipping_fee, cuisine_id, registration_date, update_date, active, open) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurant(id, name, shipping_fee, cuisine_id, registration_date, update_date, active, open) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true, true);

insert into form_payment (id, description) values (1, 'Credit card');
insert into form_payment (id, description) values (2, 'Debit card');
insert into form_payment (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'SEARCH_CUISINES', 'Allows to search cuisines');
insert into permission (id, name, description) values (2, 'EDIT_CUISINES', 'Allows to edit cuisines');

insert into restaurant_form_payment (restaurant_id, form_payment_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into product (name, description, price, active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into product (name, description, price, active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into product (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into product (name, description, price, active, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);

insert into product (name, description, price, active, restaurant_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into product (name, description, price, active, restaurant_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into role (name) values ('Manager'), ('Seller'), ('Secretary'), ('Register');
