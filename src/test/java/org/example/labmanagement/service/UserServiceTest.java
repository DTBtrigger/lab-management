package org.example.labmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void addUser() {

        User user = User.builder()
//                .id("1233445")
                .name("欧阳修")
                .account("1211367654")
                .password("1232344")
                .role(User.TEACHER)
                .telephone("14222622231")
                .build();
        userService.addUser(user);
        log.debug("添加成功");
    }

    @Test
    public void findUserByAccount() {
        User user = userService.findUserByAccount("1211367654");
        log.debug("{}",user.toString());
    }
}