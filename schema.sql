create database acme_bank;
use acme_bank;
create table accounts(
	account_id varchar(128),
    name varchar(128),
    balance decimal(6,2),
    primary key (account_id)
);

insert into accounts (account_id, name, balance) values("V9L3Jd1BBI", "fred", 100);
insert into accounts (account_id, name, balance) values("fhRq46Y6vB", "barney", 300);
insert into accounts (account_id, name, balance) values("uFSFRqUpJy", "wilma", 1000);
insert into accounts (account_id, name, balance) values("ckTV56axff", "betty", 1000);
insert into accounts (account_id, name, balance) values("Qgcnwbshbh", "pebbles", 50);
insert into accounts (account_id, name, balance) values("if9l185l18", "bambam", 50);

