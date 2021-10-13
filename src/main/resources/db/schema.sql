create table posts (
                       id serial primary key,
                       name varchar(2000),
                       description text,
                       created timestamp without time zone not null default now()
);


insert into posts (name) values ('О чем этот форум?');
insert into posts (name) values ('Правила форума.');
commit;

create table comments (
                       id serial primary key,
                       description text,
                       created timestamp without time zone not null default now(),
                       username varchar(2000),
                       post_id int references posts(id)
);

create table posts_comments (
    post_id int references posts(id),
    comments_id int references comments(id)
);