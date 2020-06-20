create table article (
    id BIGINT not null,
    annotation varchar(255),
    category varchar(255),
    text MEDIUMTEXT,
    title varchar(255),
    user_id BIGINT,
    primary key (id)
);

create table article_comments (
    article_id BIGINT not null,
    comments_id BIGINT not null
);

create table comment (
    id BIGINT not null,
    articleid BIGINT,
    author_name varchar(255),
    message varchar(255),
    user_id BIGINT,
    primary key (id)
);

create table hibernate_sequence (next_val BIGINT);
insert into hibernate_sequence values (1);
insert into hibernate_sequence values (1);
insert into hibernate_sequence values (1);

create table user (
    id BIGINT not null,
    about_me varchar(255),
    active bit not null,
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);

create table user_role (
    user_id BIGINT not null,
    roles varchar(255)
);

create table user_subscriptions (
    user_id BIGINT not null references user (id),
    subscriber_id BIGINT not null references user (id),
    primary key (user_id, subscriber_id)
);

alter table article_comments
    add constraint UK_3p3q8lmg108o11h9tix5mdfb3 unique (comments_id);

alter table article
    add constraint FKbc2qerk3l47javnl2yvn51uoi
    foreign key (user_id) references user (id);

alter table article_comments
    add constraint FKpodmjhlfsry50qirst9nv0q38
    foreign key (comments_id) references comment (id);

alter table article_comments
    add constraint FKhdo7dtp0o8cn5wo7j1cs1gokg
    foreign key (article_id) references article (id);

alter table comment
    add constraint FK8kcum44fvpupyw6f5baccx25c
    foreign key (user_id) references user (id);

alter table user_role
    add constraint FK859n2jvi8ivhui0rl0esws6o
    foreign key (user_id) references user (id);