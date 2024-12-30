package org.example.labmanagement.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.Lab;
import org.example.labmanagement.dto.EnableEquipmentCount;
import org.example.labmanagement.dto.LabCountDTO;
import org.example.labmanagement.dto.LabDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class LabRepositoryTest {
    @Autowired
    private LabRepository labRepository;

    @Test
    void countLabByState() {

        for (LabCountDTO l : labRepository.countLabByState()){
            log.debug("{}",l.toString());
        }
    }

    @Test
    void save() {
        Lab lab = Lab.builder()
                .name("云计算中心")
                .state(1)
                .quantity(67)
                .description("好好好")
                .manager("""
                        {"id":"123","name":"李四"}
                        """)
                .enableEquipment(35)
                .build();
        labRepository.save(lab);
    }

    @Test
    void countEnableEquipment() {
        for(EnableEquipmentCount e : labRepository.countEnableEquipment()){
            log.debug("{}",e.toString());
        }
    }

    @Test
    void findAllLab() {
        for (LabDTO l : labRepository.findAllLabs()) {
            log.debug(l.toString());
        }
    }


}