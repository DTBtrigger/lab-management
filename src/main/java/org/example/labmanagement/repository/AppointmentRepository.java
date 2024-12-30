package org.example.labmanagement.repository;

import org.example.labmanagement.dox.Appointment;
import org.example.labmanagement.dto.LabCountByDayofweekDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment,String> {
    @Query("""
    select dayofweek,count(distinct lab_id) as quantity from appointment where week=:week group by dayofweek;
""")
    List<LabCountByDayofweekDTO> countLabByDayofweek(int week);
}
