create table if not exists `user`
(
    id char(26) primary key ,
    name varchar(6) not null ,
    account char(10) not null ,
    password char(60) not null ,
    role char(4) not null,/**乱码长度为四*/
    telephone char(11)not null ,
    create_time datetime default current_timestamp,
    update_time datetime default current_timestamp on update current_timestamp,
    unique (account)
);

create table if not exists `course`
(

    id char(26) primary key ,/**!*/
    name varchar(20) not null ,/**!*/
    quantity tinyint unsigned not null ,/** !学生数*/
    semester char(4) not null ,/**学期，,如24-1,默认*/
    clazz varchar(30) not null,/**上课年级专业班级*****/
    type tinyint unsigned check ( 0 or 1),/**必修课，选修课*/
    teacher_id char(26) not null,/**!*/
    experiment_hour tinyint unsigned not null,/**实验学时,到时候可以校验一下（当老师选的学时还有剩余时）*/

    index(teacher_id,semester)
);

/**预约表*/
create table if not exists `appointment` (
     id char(26) primary key,/**!*/
     teacher json  not null comment '{id, name}',
     course json null comment '{id,name}',
     semester char(4) not null,
     nature varchar(4) null ,/** 性质，，临时预约,补课，其他等。到时候前端就用那个多选框约束*/
     lab_id char(26) not null ,
     lab_name varchar(10) not null,/**json当on关联时无法命中索引（bug，idea的问题，从2020到现在，也没修复），所以直接把用到的字段拍平放到相应表中*/
     week tinyint unsigned not null,/**!非负小整数*/
     dayofweek tinyint unsigned not null ,/**!非负小整数 */
/** 上课节次 */
     section tinyint unsigned not null ,/**!非负int*/
     unique(lab_id,semester,week,dayofweek,section),
     index ((cast(teacher ->> '$.id' as char(26)) collate utf8mb4_bin),(cast(course ->> '$.id' as char(26)) collate utf8mb4_bin))


);

create table if not exists `lab` (
     id char(26) primary key ,/**!*/
     name varchar(10) not null ,/**!如大数据实验室等*/
     state tinyint unsigned not null default 1,/**被维修还是可用还是啥啥啥*/
     quantity tinyint unsigned ,/**!容纳数，非负时0-255*/
     description varchar(500),/**!*/
     manager json comment '{id, name}',/**!*/
     index(state)
);

create table if not exists `news` (
     id char(26) primary key ,
     title varchar(50) not null ,
     content varchar(2000) not null ,
     author varchar(50) not null ,
     create_time datetime not null default current_timestamp,
     update_time datetime not null default current_timestamp on update current_timestamp
);

