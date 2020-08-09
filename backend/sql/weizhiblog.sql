/*
  库名 weizhiblog
  通过该文件可建立 9 张表
                1 个存储过程
                1 个定时器
 */
create table category
(
    id       int auto_increment
        primary key,
    cateName varchar(64),
    date     date,
    uid      int not null
);

# create table file
# (
#     id          bigint auto_increment
#         primary key,
#     name        varchar(100) not null comment '文件名',
#     md5         varchar(32)  null comment 'MD5值',
#     path        varchar(100) not null comment '文件路径',
#     upload_time datetime(3)  not null comment '上传时间',
#     ext         varchar(255) comment '文件后缀名'
# )
#     charset = utf8mb4;

create table roles
(
    id   int auto_increment
        primary key,
    name varchar(32)
);

create table tags
(
    id      int auto_increment
        primary key,
    tagName varchar(32),
    constraint tagName
        unique (tagName)
);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(64),
    nickname varchar(64),
    password varchar(255),
    enabled  tinyint(1) default 1,
    email    varchar(64),
    userface varchar(255),
    regTime  datetime
);

create table article
(
    id             int auto_increment
        primary key,
    title          varchar(255)         not null,
    mdContent      text comment 'md文件源码',
    htmlContent    text comment 'html源码',
    summary        text,
    cid            int                  not null,
    uid            int                  not null,
    publishDate    timestamp,
    editTime       timestamp,
    commentNum     int        default 0 not null,
    likeNum        int        default 0 not null,
    state          int comment '0表示草稿箱，1表示已发表，2表示已删除',
    pageView       int        default 0 not null,
    publicToOthers tinyint(1) default 0 not null,
    constraint article_ibfk_1
        foreign key (cid) references category (id),
    constraint article_ibfk_2
        foreign key (uid) references user (id)
);

create index cid
    on article (cid);

create index uid
    on article (uid);

create table article_tags
(
    id  int auto_increment
        primary key,
    aid int,
    tid int,
    constraint article_tags_ibfk_1
        foreign key (aid) references article (id)
            on delete cascade,
    constraint article_tags_ibfk_2
        foreign key (tid) references tags (id)
);

create index tid
    on article_tags (tid);

create table comments
(
    id          int auto_increment
        primary key,
    aid         int,
    content     text,
    publishTime timestamp null,
    parentId    int comment '-1表示正常回复，其他值表示是评论的回复',
    uid         int,
    constraint comments_ibfk_1
        foreign key (aid) references article (id),
    constraint comments_ibfk_2
        foreign key (uid) references user (id)
);

create index aid
    on comments (aid);

create index parentId
    on comments (parentId);

create index uid
    on comments (uid);

create table data
(
    id         int auto_increment
        primary key,
    uid        int,
    aid        int,
    day        timestamp default CURRENT_TIMESTAMP,
    pv         int,
    commentNum int,
    likeNum    int,
    constraint data_article_aid_id_fk
        foreign key (aid) references article (id),
    constraint data_user_uid_id
        foreign key (uid) references user (id)
);

create table roles_user
(
    id  int auto_increment
        primary key,
    rid int default 2,
    uid int,
    constraint roles_user_ibfk_1
        foreign key (rid) references roles (id),
    constraint roles_user_ibfk_2
        foreign key (uid) references user (id)
            on delete cascade
);

create index rid
    on roles_user (rid);

create
    definer = root@localhost procedure countData()
begin
    declare aid int default 0;
    declare total int default 0;
    declare uid int default 0;
    declare day timestamp default current_timestamp();
    declare pageView int default 0;
    declare commentNum int default 0;
    declare likeNum int default 0;
    declare done int default 0;
    DECLARE cur CURSOR FOR SELECT a.id, a.uid, a.pageView, a.likeNum, a.commentNum from article a;
    -- 指定游标循环结束时的返回值
    declare continue handler for not found set done = 1;
    -- 打开游标
    open cur;
    -- 初始化 变量
    set total = 0;
    -- while 循环
    while done != 1
        do
            fetch cur into aid,uid,pageView,likeNum,commentNum;
            insert into data(`uid`, `aid`, `day`, `pv`, `commentNum`, `likeNum`)
            values (uid, aid, day, pageView, likeNum, commentNum);
        end while;
    -- 关闭游标
    close cur;
end;

create definer = root@localhost event countData on schedule
    every '24' HOUR
    on completion preserve
    enable
    do
    call countData();


insert into roles (id, name) value (1, '超级管理员');
insert into roles (id, name) value (2, '用户');


