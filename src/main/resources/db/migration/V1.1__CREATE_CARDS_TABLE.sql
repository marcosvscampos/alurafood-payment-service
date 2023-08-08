CREATE TABLE cards (
 id varchar(36) NOT NULL,
 number varchar(19) DEFAULT NULL,
 expiration varchar(7) DEFAULT NULL,
 code varchar(3) DEFAULT NULL,
PRIMARY KEY(id)
)