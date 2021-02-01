-- auto-generated definition
create table bot_users
(
    id    serial not null
        constraint bot_users_pk
            unique,
    tg_id oid
);

alter table bot_users
    owner to postgres;

create index bot_users_tg_id_index
    on bot_users (tg_id);
