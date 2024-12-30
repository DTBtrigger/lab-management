package org.example.labmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.dto.LabCountByDayofweekDTO;
import org.example.labmanagement.dto.LabCountDTO;
import org.example.labmanagement.dto.LabDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class AdminServiceTest {
    @Autowired
    AdminService adminService;

    @Test
    void  adduser() {
        User user = User.builder()
                .name("上面名字肯定是心取的")
                .account("2022222222")
                .password("1234")
                .role(User.TEACHER)
                .telephone("12345678999")
                .build();
        adminService.addSingleUser(user);
    }

    @Test
    void countLabByState() {

        for (LabCountDTO l : adminService.countLabByState()){
            log.debug("{}",l.toString());
        }
    }

    @Test
    void countLabByDayofweek() {
        for (LabCountByDayofweekDTO l : adminService.countLabByDayofweek(2)) {
            log.debug("{}",l.toString());
        }
    }

    @Test
    void getLabState() {
        Map<String, List<?>> labState =  adminService.getLabState(2);
        labState.forEach((key, value) -> {
            log.debug("{},{}",key,value.toString());
//            System.out.println("Key: " + key + ", Value: " + value);
        });

    }

    @Test
    void findAllLabs() {
        for (LabDTO l : adminService.findAllLabs()) {
            log.debug(l.toString());
        }
    }

}