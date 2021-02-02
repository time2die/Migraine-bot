create table bot_answers
(
    id          serial not null
        constraint bot_answers_pk
            primary key,
    tg_id       oid    not null,
    question_id oid    not null,
    answer      text,
    write_time  TIME   NOT NULL DEFAULT now()
);

create index bot_answers_tgid_questionid_uindex
    on bot_answers (tg_id, question_id);

drop index if exists bot_answers_tgid_questionid_uindex;
drop table if exists bot_answers;

