drop table if exists users CASCADE;
create table users (
     id        bigint generated by default as identity,
     user_name varchar(10),
     phone_num varchar(25),
     primary key (id)
);