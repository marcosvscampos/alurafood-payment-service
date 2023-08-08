CREATE TABLE payments (
 id varchar(36) NOT NULL,
 payment_value decimal(19,2) NOT NULL,
 name varchar(100) DEFAULT NULL,
 number varchar(19) DEFAULT NULL,
 expiration varchar(7) DEFAULT NULL,
 code varchar(3) DEFAULT NULL,
 status varchar(255) NOT NULL,
 payment_method_id varchar(36) NOT NULL,
 order_id varchar(36) NOT NULL,
PRIMARY KEY (id)
);