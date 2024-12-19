create table if not exists `user`
(
    id char(26) primary key ,
    account varchar(10) not null ,
    password char(60) not null ,
    telephone char(11) not null ,
    role char(3) not null,/**乱码长度为三*/
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,

    unique (account)
);

create table if not exists `course`
(
    id char(26) primary key ,
    name varchar(10) not null ,
    quantity tinyint unsigned not null ,/**学生人数*/
    lesson tinyint unsigned not null ,
    teacher_id char(26) not null,

    index (teacher_id)
);

create table if not exists `lab`
(
    id char(26) primary key ,
    name varchar(10) not null ,/**名字可能为“大数据实验室”*/
    manage varchar(6) null ,/**可能存在实验室建好了但是还没分配管理员的情况*/
    quatity tinyint unsigned null ,
    description varchar(200) null,
    state tinyint unsigned not null default 1,
    index (state)
);

create table if not exists `appointment`
(
    id char(26) primary key ,
    lab_id char(26) not null ,
    week tinyint unsigned not null ,
    dayofweek tinyint unsigned not null ,
    section tinyint unsigned not null ,
    course_id char(26) not null ,

    unique (lab_id,week,dayofweek,section) ,/**唯一约束自带索引，以第一个属性为索引*/
    index(course_id)
);

create table if not exists `appoinment_json`
(
    id char(26) primary key ,
    course json not null comment '{id,name}',
    teacher json not null comment '{id,name}',
    lab json not null comment '{id,name}',
    week tinyint unsigned not null ,
    dayofweek tinyint unsigned not null ,
    section tinyint unsigned not null ,

    unique ((cast (lab ->> '$.id' as char(26) ) collate utf8mb4_bin), week, dayofweek, section),


)
