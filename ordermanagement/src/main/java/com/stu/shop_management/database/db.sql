create database shop_db;

create table products(
                         product_id bigserial primary key,
                         product_name varchar(255) not null unique,
                         price decimal(10,2) not null
);

create table customers(
                          customer_id bigserial primary key,
                          customer_name varchar(255) not null,
                          email varchar(255) unique
);

create table orders(
                       order_id bigserial primary key,
                       customer_id bigint references customers(customer_id),
                       order_date date not null,
                       total_amount decimal(10,2) not null
);