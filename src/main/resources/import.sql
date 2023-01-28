insert into cuisine(id, name) values(1, 'Indian');
insert into cuisine(id, name) values(2, 'Thai');

insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into restaurant(name, shipping_fee, cuisine_id, active, open, address_zip, address_street, address_number, address_neighborhood, address_city_id) values('Tuk Tuk Indian Food', 15, 1, true, true, '009922', 'Rua Joao', '12', 'Centro',1);
insert into restaurant(name, shipping_fee, cuisine_id, active, open) values('Thai Gourmet', 10, 2, true, true);
insert into restaurant(name, shipping_fee, cuisine_id, active, open) values('Thai Delivery', 9.50, 2, true, true);

insert into form_payment (id, description) values (1, 'Credit card');
insert into form_payment (id, description) values (2, 'Debit card');
insert into form_payment (id, description) values (3, 'Cash');

insert into permission (id, name, description) values (1, 'SEARCH_CUISINES', 'Allows to search cuisines');
insert into permission (id, name, description) values (2, 'EDIT_CUISINES', 'Allows to edit cuisines');

insert into restaurant_form_payment (restaurant_id, form_payment_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);