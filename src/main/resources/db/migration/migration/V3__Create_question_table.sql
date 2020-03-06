create table question
(
    id int not null,
    title varchar(50) not null,
    description TEXT,
    gmtCreate bigint,
    gmtModified bigint,
    creator int not null,
    commentCount int default 0,
    viewCount int default 0,
    likeCount int default 0,
    tag varchar(256),
    constraint question_pk
        primary key (id)
);

comment on table question is '发布问题';

