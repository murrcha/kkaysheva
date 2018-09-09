create database items_store;

create table roles (
    id serial primary key,
    role_name character varying (30)
);

create table rules (
    id serial primary key,
    rule_name character varying (30)
);

create table role_rule (
    id serial primary key,
    role_id integer references roles(id),
    rule_id integer references rules(id)
);

create table users (
    id serial primary key,
    login character varying(50),
    password character varying(50),
    create_date timestamp,
    role_id integer references roles(id)
);

create table categories (
    id serial primary key,
    category_name character varying(30)
);

create table states (
    id serial primary key,
    state_name character varying(30)
);

create table items (
    id serial primary key,
    description character varying(2000),
    create_date timestamp,
    user_id integer references users(id),
    state_id integer references states(id),
    category_id integer references categories(id)
);

create table comments (
    id serial primary key,
    text character varying(2000),
    create_date timestamp,
    user_id integer references users(id),
    item_id integer references items(id)
);

create table attaches (
    id serial primary key,
    attach character varying(2000),
    user_id integer references users(id),
    item_id integer references items(id)
);

insert into roles (role_name) values ('administrator');
insert into roles (role_name) values ('user');
insert into rules (rule_name) values ('read');
insert into rules (rule_name) values ('write');
insert into rules (rule_name) values ('add');
insert into rules (rule_name) values ('delete');
insert into role_rule (role_id, rule_id) values (1, 1);
insert into role_rule (role_id, rule_id) values (1, 2);
insert into role_rule (role_id, rule_id) values (1, 3);
insert into role_rule (role_id, rule_id) values (1, 4);
insert into role_rule (role_id, rule_id) values (2, 1);
insert into role_rule (role_id, rule_id) values (2, 2);
insert into users (login, password, create_date) values ('admin', 'admin', '2018-09-09 00:00:00');
insert into users (login, password, create_date) values ('kkaysheva', 'kkaysheva', '2018-09-09 00:00:00');
insert into categories (category_name) values ('question');
insert into categories (category_name) values ('offer');
insert into categories (category_name) values ('complaint');
insert into categories (category_name) values ('thanks');
insert into states (state_name) values ('to do');
insert into states (state_name) values ('in progress');
insert into states (state_name) values ('done');
insert into items (description, create_date, user_id, state_id, category_id)
values ('description for item number 1', '2018-09-09 00:00:00', 2, 1, 1);
insert into items (description, create_date, user_id, state_id, category_id)
values ('description for item number 2', '2018-09-09 00:00:00', 1, 1, 1);
insert into comments (text, create_date, user_id, item_id)
values ('comment for item number 1', '2018-09-09 00:00:00', 1, 1);
insert into comments (text, create_date, user_id, item_id)
values ('comment for item number 2', '2018-09-09 00:00:00', 2, 1);
insert into attaches (attach, user_id, item_id)
values ('link to attach for item 1', 1, 1);
insert into attaches (attach, user_id, item_id)
values ('link to attach for item 2', 1, 2);



