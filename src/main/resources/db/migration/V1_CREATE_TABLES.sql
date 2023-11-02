create table hibernate_sequence
(
    next_val biint
) engine=MyISAM;

insert into hibernate_sequence
values (1);
insert into hibernate_sequence
values (1);

CREATE TABLE user
(
    id       BIGINT      NOT NULL AUTO_INCREMENT,
    password varchar(64) not null,
    username varchar(64) not null unique,
    PRIMARY KEY (id)
) engine=MyISAM;

CREATE TABLE task
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    title       varchar(64) not null,
    description varchar(64) not null,
    completed   BOOLEAN     NOT NULL,
    PRIMARY KEY (id)
) engine=MyISAM;
