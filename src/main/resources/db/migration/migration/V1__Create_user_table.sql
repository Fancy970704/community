create table USER
(
    ID           INTEGER auto_increment,
    ACCOUNT_ID   VARCHAR(50) not null,
    NAME         VARCHAR(20),
    TOKEN        VARCHAR(50) not null,
    GMT_CREATE   BIGINT      not null,
    GMT_MODIFIED BIGINT      not null,
    constraint USER_PK
        primary key (ACCOUNT_ID),
    constraint USER_TOKEN_UINDEX
        unique (TOKEN)
);