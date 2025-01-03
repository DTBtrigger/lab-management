package org.example.labmanagement.repository;

import org.example.labmanagement.dox.Lab;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.dto.EnableEquipmentCount;
import org.example.labmanagement.dto.LabCountDTO;
import org.example.labmanagement.dto.LabDTO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepository extends CrudRepository<Lab,String> {
    @Query("""
        SELECT state, count(state) as quantity from lab group by state
""")
    List<LabCountDTO> countLabByState();

    @Query("""
        select name,enable_equipment as enable_quantity,(quantity-enable_equipment) as unable_quantity from lab
""")
    List<EnableEquipmentCount> countEnableEquipment();

    @Query("""
            select id,name,state,quantity,description,manager from lab;
""")
    List<LabDTO> findAllLabs();

//    @Query("""
//
//""")

}
