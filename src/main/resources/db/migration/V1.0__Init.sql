CREATE TABLE cities (
    id      bigserial,
    country varchar(255) not null,
    name    varchar(255) not null
);

alter table cities
    owner to username;

create unique index cities_id_uindex
    on cities (id);

create index cities_country_index
    on cities (country);

create index cities_name_index
    on cities (name);