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

