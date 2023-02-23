CREATE TABLE ordering (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cancellation_date DATETIME,
    confirmation_date DATETIME,
    address_complement VARCHAR(60),
    address_neighborhood VARCHAR(60) NOT NULL,
    address_number VARCHAR(20) NOT NULL,
    address_street VARCHAR(100) NOT NULL,
    address_zip VARCHAR(10) NOT NULL,
    delivery_date DATETIME,
    registration_date DATETIME NOT NULL,
    shipping_fee DECIMAL(10 , 2 ) NOT NULL,
    status VARCHAR(10) NOT NULL,
    subtotal DECIMAL(10 , 2 ) NOT NULL,
    total_value DECIMAL(10 , 2 ) NOT NULL,
    client_user_id BIGINT NOT NULL,
    address_city_id BIGINT,
    form_payment_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    PRIMARY KEY (id),

    CONSTRAINT fk_ordering_app_user FOREIGN KEY (client_user_id)
        REFERENCES app_user (id),
    CONSTRAINT fk_ordering_address_city FOREIGN KEY (address_city_id)
        REFERENCES city (id),
    CONSTRAINT fk_ordering_form_payment FOREIGN KEY (form_payment_id)
        REFERENCES form_payment (id),
    CONSTRAINT fk_ordering_restaurant FOREIGN KEY (restaurant_id)
        REFERENCES restaurant (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE order_item (
    id BIGINT NOT NULL AUTO_INCREMENT,
    observation VARCHAR(255),
    quantity INTEGER NOT NULL,
    total_price DECIMAL(10 , 2 ) NOT NULL,
    unit_price DECIMAL(10 , 2 ) NOT NULL,
    ordering_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (id),

    CONSTRAINT fk_order_item_ordering FOREIGN KEY (ordering_id)
        REFERENCES ordering (id),
    CONSTRAINT fk_order_item_product FOREIGN KEY (product_id)
        REFERENCES product (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;
