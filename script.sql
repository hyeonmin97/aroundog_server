create table dog
(
    id    bigint auto_increment
        primary key,
    breed varchar(255) null
);

create table user
(
    id        bigint auto_increment
        primary key,
    age       int                      null,
    email     varchar(255)             null,
    gender    varchar(255)             null,
    image     int                      not null,
    password  varchar(255)             null,
    phone     varchar(255)             null,
    user_id   varchar(255)             null,
    user_name varchar(255)             null,
    hate_dog  varchar(255) default '$' null,
    constraint email_UNIQUE
        unique (email)
);

create table user_dog
(
    id     bigint auto_increment
        primary key,
    age    int          null,
    gender varchar(255) null,
    height double       null,
    name   varchar(255) null,
    weight double       null,
    dog    bigint       null,
    user   bigint       null,
    constraint FK_USERDOG_DOG
        foreign key (dog) references dog (id),
    constraint FK_USERDOG_USER
        foreign key (user) references user (id)
);

create table coordinate
(
    user_dog_id bigint       not null
        primary key,
    latitude    double       null,
    longitude   double       null,
    tile        varchar(255) null,
    time_stamp  datetime(6)  null,
    walking     bit          null,
    constraint FK_COOR_USERDOG
        foreign key (user_dog_id) references user_dog (id)
);

create table dog_img
(
    id          bigint auto_increment
        primary key,
    user_dog_id bigint       not null,
    path        varchar(255) not null,
    filename    varchar(255) not null,
    constraint FK_DOGIMG_USERDOG
        foreign key (user_dog_id) references user_dog (id)
);

create table walk
(
    id            bigint auto_increment
        primary key,
    course        longtext                 null,
    course_center varchar(255)             null,
    end_time      datetime(6)              null,
    img           varchar(255)             null,
    start_time    datetime(6)              null,
    user          bigint                   null,
    tile          varchar(255)             null,
    second        bigint       default 0   null,
    distance      bigint       default 0   null,
    dog_ids       varchar(255) default '%' null,
    hash          longtext                 null,
    constraint FK_WALK_USER
        foreign key (user) references user (id)
);

create table walk_deduplication
(
    id       bigint auto_increment
        primary key,
    rdp      longtext      not null,
    hash     varchar(255)  not null,
    img      varchar(255)  null,
    tile     varchar(255)  null,
    second   bigint        null,
    distance bigint        null,
    good     int default 0 null,
    bad      int default 0 null,
    address  varchar(255)  null
);

create table bad
(
    id         bigint auto_increment
        primary key,
    user_id    bigint null,
    de_walk_id bigint null,
    constraint FK_BAD_DEWALK
        foreign key (de_walk_id) references walk_deduplication (id),
    constraint FK_BAD_USER
        foreign key (user_id) references user (id)
);

create table good
(
    id         bigint auto_increment
        primary key,
    user_id    bigint null,
    de_walk_id bigint null,
    constraint FK_HEART_DEWALK
        foreign key (de_walk_id) references walk_deduplication (id),
    constraint FK_HEART_USER
        foreign key (user_id) references user (id)
);