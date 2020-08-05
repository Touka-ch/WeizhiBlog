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
    cateName varchar(64) ,
    date     date        ,
    uid      int         not null
);

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
    username varchar(64)          ,
    nickname varchar(64)          ,
    password varchar(255)         ,
    enabled  tinyint(1) default 1 ,
    email    varchar(64)          ,
    userface varchar(255)         ,
    regTime  datetime
);

create table article
(
    id             int auto_increment
        primary key,
    title          varchar(255)         not null,
    mdContent      text                  comment 'md文件源码',
    htmlContent    text                  comment 'html源码',
    summary        text                 ,
    cid            int                  not null,
    uid            int                  not null,
    publishDate    timestamp            ,
    editTime       timestamp            ,
    commentNum     int        default 0 not null,
    likeNum        int        default 0 not null,
    state          int                   comment '0表示草稿箱，1表示已发表，2表示已删除',
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
    aid int ,
    tid int ,
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
    aid         int       ,
    content     text      ,
    publishTime timestamp ,
    parentId    int        comment '-1表示正常回复，其他值表示是评论的回复',
    uid         int       ,
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
    uid        int                                 ,
    aid        int                                 ,
    day        timestamp default CURRENT_TIMESTAMP ,
    pv         int                                 ,
    commentNum int                                 ,
    likeNum    int                                 ,
    constraint data_article_aid_id_fk
        foreign key (aid) references article (id),
    constraint data_user_uid_id
        foreign key (uid) references user (id)
);

create table roles_user
(
    id  int auto_increment
        primary key,
    rid int default 2 ,
    uid int           ,
    constraint roles_user_ibfk_1
        foreign key (rid) references roles (id),
    constraint roles_user_ibfk_2
        foreign key (uid) references user (id)
            on delete cascade
);

create index rid
    on roles_user (rid);

delimiter //
create procedure countData()
begin
    declare aid int default 0;
    declare total int default 0;
    declare uid int default 0;
    declare day timestamp default current_timestamp();
    declare pageView int default 0;
    declare commentNum int default 0;
    declare likeNum int default 0;
    declare done int default 0;
    DECLARE cur CURSOR FOR SELECT a.id,a.uid,a.pageView,a.likeNum,a.commentNum from article a;
    -- 指定游标循环结束时的返回值
    declare continue handler for not found set done = 1;
    -- 打开游标
    open cur;
    -- 初始化 变量
    set total = 0;
    -- while 循环
    while done != 1 do
            fetch cur into aid,uid,pageView,likeNum,commentNum;
            insert into data(`uid`,`aid`,`day`,`pv`,`commentNum`,`likeNum`) values (uid,aid,day,pageView,likeNum,commentNum);
        end while;
    -- 关闭游标
    close cur;
end//
delimiter ;

create event countData on schedule
    every '24' HOUR
    on completion preserve
    enable
    do
    call countData();

