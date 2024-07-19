create table if not exists clients (
    id bigserial not null primary key,
    name varchar(255) not null
);

create table if not exists petitions (
    id bigserial not null primary key,
    content varchar,
    date_created timestamp,
    client_id bigint not null references clients(id) on delete cascade
);