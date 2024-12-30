package org.example.labmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void addUser() {

        User user = User.builder()
//                .id("1233445")
                .name("郭靖")
                .account("1271367654")
                .password(passwordEncoder.encode("1232344"))
                .role(User.ADMIN)
                .telephone("14222677731")
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