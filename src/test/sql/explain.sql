SHOW INDEX FROM appointment;

explain
select *
from lab l left join appointment a
    on  l.id = a.lab_id and a.semester='24-1' and a.week=1  and a.dayofweek=2 and a.section=3
where a.lab_id is null and l.state=1;

explain
select *
from appointment a where
                     and
                     a.week=1 and a.dayofweek=2;

explain
select *
from lab l
where l.state = 1
  and not exists (
    select 1
    from appointment a
    where l.id = a.lab_id
      and a.week = 1
      and a.dayofweek = 2
);

explain
select *
from lab l
         left join appointment a
                   on l.id = a.lab ->> '$.id'
where l.state = 1
  and a.week = 1
  and a.dayofweek = 2
  and a.lab ->> '$.id' is null;


INSERT INTO `lab`  VALUES
    ('5f6a4e8d6c40437a8f22c8c9', '大数据实验室', 1, 50, '专注于大数据分析与处理', '{"id": "1", "name": "张三"}',50),
    ('5f6a4e8d6c40437a8f22c8ca', '人工智能实验室', 1, 30, '研究人工智能与机器学习', '{"id": "2", "name": "李四"}',30),
    ('5f6a4e8d6c40437a8f22c8cb', '网络安全实验室', 0, 20, '维护网络安全与防御', '{"id": "3", "name": "王五"}',15),
    ('5f6a4e8d6c40437a8f22c8cc', '嵌入式系统实验室', 1, 40, '开发嵌入式系统与硬件', '{"id": "4", "name": "赵六"}',39),
    ('5f6a4e8d6c40437a8f22c8cd', '数据库实验室', 1, 60, '数据库管理与优化', '{"id": "5", "name": "孙七"}',55),
    ('5f6a4e8d6c40437a8f22c8ce', '云计算实验室', 2, 90, '云计算平台与资源', '{"id": "6", "name": "周八"}',90),
    ('5f6a4e8d6c40437a8f22c8cf', '物联网实验室', 1, 25, '物联网设备与通信', '{"id": "7", "name": "吴九"}',23),
    ('5f6a4e8d6c40437a8f22c8d0', '软件工程实验室', 1, 80, '软件开发与测试', '{"id": "8", "name": "郑十"}',56),
    ('5f6a4e8d6c40437a8f22c8d1', '计算机图形学实验室', 1, 10, '计算机图形与渲染', '{"id": "9", "name": "陈十一"}',10),
    ('5f6a4e8d6c40437a8f22c8d2', '虚拟现实实验室', 1, 35, '虚拟现实与增强现实', '{"id": "10", "name": "刘十二"}',35);

explain
SELECT state, count(state) as number from lab group by state;
explain
select dayofweek,count(distinct lab_id) from appointment where week=2 group by dayofweek;
explain
select name,enable_equipment, (quantity-lab.enable_equipment) as unable_equipment from lab;
select title,content,author,update_time from news;
select name,state,quantity,description,(lab.manager ->> '$.name') as manager_name
from lab;