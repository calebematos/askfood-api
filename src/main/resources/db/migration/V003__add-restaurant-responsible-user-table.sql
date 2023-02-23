CREATE TABLE restaurant_responsible_user (
    restaurant_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (restaurant_id , user_id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;

ALTER TABLE restaurant_responsible_user ADD CONSTRAINT fk_restaurant_user_restaurant
FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);

ALTER TABLE restaurant_responsible_user ADD CONSTRAINT fk_restaurant_user_user
FOREIGN KEY (user_id) REFERENCES app_user (id);