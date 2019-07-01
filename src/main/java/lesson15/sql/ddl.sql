create table if not exists "user"
(
	id serial not null,
	name text,
	birthday timestamp,
	login_id bigint,
	city text,
	email text,
	description text
);

alter table "user" owner to postgres;

create unique index if not exists user_id_uindex
	on "user" (id);

create table role
(
    id          serial not null,
    name        text,
    description text
);

alter table role
    owner to postgres;

create unique index role_id_uindex
    on role (id);

INSERT INTO public.role (id, name, description) VALUES (1, 'Administration', 'admin');
INSERT INTO public.role (id, name, description) VALUES (2, 'Clients', 'clients');
INSERT INTO public.role (id, name, description) VALUES (3, 'Billing', 'billing');

create table if not exists user_role
(
	id serial not null,
	user_id integer not null,
	role_id integer not null
);

alter table user_role owner to postgres;

create unique index if not exists user_role_id_uindex
	on user_role (id);

