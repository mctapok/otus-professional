create table Clients (
    id serial primary key not null,
    name varchar(255) not null,
    address_id int references addresses (id) on delete cascade
);

create table Phones (
    id serial primary key not null,
    phone_number varchar(255),
    client_id int references clients (id) on delete cascade
);

create table Addresses (
    id serial primary key not null,
    street varchar(255)
);