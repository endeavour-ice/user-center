-- auto-generated definition
create table user
(
    id           bigint                             not null comment 'id'
        primary key,
    username     varchar(256)                       null comment '用户名',
    user_account varchar(256)                       null comment '登陆账号',
    avatar_url   varchar(1024)                      null comment '用户头像',
    gender       varchar(10)                        null comment '性别',
    password     varchar(32)                        null comment '密码',
    tel          varchar(256)                       null comment '手机号',
    email        varchar(256)                       null comment '邮箱',
    profile      varchar(255)                       null comment '个人简介',
    planet_code  varchar(512)                       null comment '编号',
    tags         varchar(1024)                      null comment '标签列表',
    user_status  int      default 0                 not null comment '用户状态',
    role         int      default 0                 not null comment '用户角色 0 - 普通用户 1 - 管理员',
    is_delete    tinyint  default 0                 not null comment '是否删除',
    create_time  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '用户表';

create table user_friend
(
    id          varchar(255)                       not null comment 'id'
        primary key,
    user_id     varchar(255)                       null comment '用户id',
    friends_id  varchar(255)                       null comment '朋友id',
    comments    varchar(255)                       null comment '朋友备注',
    create_time datetime default CURRENT_TIMESTAMP null comment '添加好友日期'
);
create table user_friend_req
(
    id          varchar(255)                       not null
        primary key,
    from_userid varchar(255)                       null comment '请求用户id',
    to_userid   varchar(255)                       null comment '被请求好友用户',
    message     varchar(255)                       null comment '发送的消息',
    user_status int(1)   default 0                 not null comment '消息是否已处理 0 未处理',
    create_time datetime default CURRENT_TIMESTAMP null
);

create table chat_record
(
    id          varchar(255)                       not null
        primary key,
    user_id     varchar(255)                       null comment '用户id
',
    friend_id   varchar(255)                       null comment '好友id',
    has_read    int(1)   default 0                 null comment '是否已读 0 未读',
    create_time datetime default CURRENT_TIMESTAMP not null,
    is_delete   tinyint  default 0                 not null comment '是否删除',
    message     varchar(1024)                      null comment '消息'
)
    comment '聊天记录表';

create table team
(
    id           bigint                             not null comment 'id'
        primary key,
    name     varchar(256)                       null comment '队伍的名称',
    user_id bigint                       null comment '用户id',
    description   varchar(1024)                      null comment '描述',
    max_num       bigint(20)                      default 1 not null comment '最大人数',
    password     varchar(32)                        null comment '密码',
    status          varchar(256)                       null comment '状态',
    expire_time        datetime                      null comment '创建队伍的时间',
    is_delete    tinyint  default 0                 not null comment '是否删除',
    create_time  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '队伍表';

create table user_team
(
    id           bigint                             not null comment 'id'
        primary key,
    name     varchar(256)                       null comment '队伍的名称',
    user_id bigint                       null comment '用户id',
    team_id   bigint                      null comment '队伍id',
    join_time  datetime                      null comment '加入队伍时间',
    is_delete    tinyint  default 0                 not null comment '是否删除',
    create_time  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '队伍表';