CREATE TABLE product_photo (
    product_id BIGINT NOT NULL,
    file_name VARCHAR(150) NOT NULL,
    description VARCHAR(150),
    content_type VARCHAR(80) NOT NULL,
    size INT NOT NULL,
    PRIMARY KEY (product_id),
    CONSTRAINT fk_product_photo_product FOREIGN KEY (product_id)
        REFERENCES product (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;