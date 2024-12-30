package org.example.labmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dto.LabCountDTO;
import org.example.labmanagement.vo.ResultVO;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class AdminControllerTest {
    @Autowired
    AdminController adminController;

    @Test
    void count() {
        ResultVO resultVO = adminController.accountLabByState();
        log.debug(resultVO.toString());

    }

}