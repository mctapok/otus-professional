drop table if exists users;

create table users(
id int auto_increment primary key,
login varchar(255),
password varchar(255));