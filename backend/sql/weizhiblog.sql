/*
 *
 * @createTime 07-30 18:29:58
 * @author Touka_
 * @classname C:/Workplace/Project/WeizhiBlog/backend/sql/weizhiblog.sql
 * @lastModifiedTime 7月30日   18:29:57
 */

# 这是我修改后的数据库，请将之前的库删掉，然后运行此文件。
create database weizhiblog;
use weizhiblog;
create table category
(
    id       int auto_increment
        primary key,
    cateName varchar(64) null,
    date     date        null,
    uid      int         not null
);

create table roles
(
    id   int auto_increment
        primary key,
    name varchar(32) null
);

create table tags
(
    id      int auto_increment
        primary key,
    tagName varchar(32) null,
    constraint tagName
        unique (tagName)
);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(64)          null,
    nickname varchar(64)          null,
    password varchar(255)         null,
    enabled  tinyint(1) default 1 null,
    email    varchar(64)          null,
    userface varchar(255)         null,
    regTime  datetime             null
);

create table article
(
    id             int auto_increment
        primary key,
    title          varchar(255)         not null,
    mdContent      text                 null comment 'md文件源码',
    htmlContent    text                 null comment 'html源码',
    summary        text                 null,
    cid            int                  not null,
    uid            int                  not null,
    publishDate    timestamp            null,
    editTime       timestamp            null,
    commentNum     int        default 0 not null,
    likeNum        int        default 0 not null,
    state          int                  null comment '0表示草稿箱，1表示已发表，2表示已删除',
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
    aid int null,
    tid int null,
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
    aid         int       null,
    content     text      null,
    publishTime timestamp null,
    parentId    int       null comment '-1表示正常回复，其他值表示是评论的回复',
    uid         int       null,
    constraint comments_ibfk_1
        foreign key (aid) references article (id),
    constraint comments_ibfk_2
        foreign key (uid) references user (id),
    constraint comments_ibfk_3
        foreign key (parentId) references comments (id)
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
    uid        int                                 null,
    aid        int                                 null,
    day        timestamp default CURRENT_TIMESTAMP null,
    pv         int                                 null,
    commentNum int                                 null,
    likeNum    int                                 null,
    constraint data_article_aid_id_fk
        foreign key (aid) references article (id),
    constraint data_user_uid_id
        foreign key (uid) references user (id)
);

create table roles_user
(
    id  int auto_increment
        primary key,
    rid int default 2 null,
    uid int           null,
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
        starts '2020-07-29 10:32:44'
    on completion preserve
    enable
    do
    call countData();

