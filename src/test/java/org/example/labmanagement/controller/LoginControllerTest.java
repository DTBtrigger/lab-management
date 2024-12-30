package org.example.labmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.dto.LabCountDTO;
import org.example.labmanagement.service.UserService;
import org.example.labmanagement.vo.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class LoginControllerTest {
    @Autowired
    private LoginController loginController;
    @Autowired
    private UserService userService;


    @Test
    public void loginTest() {
//        LabCountDTO l = loginController.login();
        User user = userService.findUserByAccount("1211367654");
//        ResultVO r = loginController.login(user);
        log.debug(user.toString());
//        log.debug(r.toString());

    }

}