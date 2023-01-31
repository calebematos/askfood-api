CREATE TABLE app_user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(80) NOT NULL,
    password VARCHAR(255),
    registration_date DATETIME NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE city (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80),
    state_id BIGINT,
    PRIMARY KEY (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE cuisine (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60),
    PRIMARY KEY (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE form_payment (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(60),
    PRIMARY KEY (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE permission (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE product (
    id BIGINT NOT NULL AUTO_INCREMENT,
    active BIT NOT NULL,
    description TEXT NOT NULL,
    name VARCHAR(80) NOT NULL,
    price DECIMAL(10 , 2 ) NOT NULL,
    restaurant_id BIGINT NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE restaurant (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cuisine_id BIGINT NOT NULL,
    name VARCHAR(80) NOT NULL,
    active TINYINT(1) NOT NULL,
    open TINYINT(1) NOT NULL,
    registration_date DATETIME NOT NULL,
    shipping_fee DECIMAL(10 , 2 ) NOT NULL,
    update_date DATETIME NOT NULL,

    address_complement VARCHAR(60),
    address_neighborhood VARCHAR(60),
    address_number VARCHAR(20),
    address_street VARCHAR(100),
    address_zip VARCHAR(10),
    address_city_id BIGINT,
    PRIMARY KEY (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE restaurant_form_payment (
    restaurant_id BIGINT NOT NULL,
    form_payment_id BIGINT NOT NULL,

    PRIMARY KEY (restaurant_id, form_payment_id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE role (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE role_permission (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE state (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, role_id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;


ALTER TABLE city ADD CONSTRAINT fk_city_state
FOREIGN KEY (state_id) REFERENCES state (id);

ALTER TABLE product ADD CONSTRAINT fk_product_restaurant
FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);

ALTER TABLE restaurant ADD CONSTRAINT fk_restaurant_city
FOREIGN KEY (address_city_id) REFERENCES city (id);

ALTER TABLE restaurant ADD CONSTRAINT fk_restaurant_cuisine
FOREIGN KEY (cuisine_id) REFERENCES cuisine (id);

ALTER TABLE restaurant_form_payment ADD CONSTRAINT fk_restaurant_form_payment_form_payment
FOREIGN KEY (form_payment_id) REFERENCES form_payment (id);

ALTER TABLE restaurant_form_payment ADD CONSTRAINT fk_restaurant_form_payment_restaurant
FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);

ALTER TABLE role_permission ADD CONSTRAINT fk_role_permission_permission
FOREIGN KEY (permission_id) REFERENCES permission (id);

ALTER TABLE role_permission ADD CONSTRAINT fk_role_permission_role
FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE user_role ADD CONSTRAINT fk_user_role_role
FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE user_role ADD CONSTRAINT fk_user_role_app_user
FOREIGN KEY (user_id) REFERENCES app_user (id);
