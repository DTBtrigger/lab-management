package org.example.labmanagement.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void save() {
        User user = User.builder()
//                .id("1233445")
                .name("杨过")
                .account("1234567890")
                .password("1234")
                .role(User.TEACHER)
                .telephone("12345678981")
                .build();
        userRepository.save(user);
    }

    @Test
    void findUserByAccount() {
        User user = userRepository.findUserByAccount("1234567890");
        log.debug("{}",user.toString());
    }
}