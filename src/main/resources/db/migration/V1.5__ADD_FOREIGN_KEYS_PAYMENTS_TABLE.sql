alter table payments add constraint fk_payment_method foreign key (payment_method_id) references payment_methods(id);

alter table payments add card_id varchar(36) not null;
alter table payments add constraint fk_card_id foreign key (card_id) references cards(id);