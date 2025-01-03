package org.example.labmanagement.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void save() {
        User user = User.builder()
//                .id("1233445")
                .name("钱七")
                .account("3334867890")
                .password("123421")
                .role(User.TEACHER)
                .telephone("12745679981")
                .build();
        userRepository.save(user);
    }

    @Test
    void findUserByAccount() {
        User user = userRepository.findUserByAccount("1234567890");
        log.debug("{}",user.toString());
    }

    @Test
    void findAllUsers() {
        List<User> users = userRepository.findAllUser();
        for (User u : users) {
            log.debug(u.toString());
        }
    }

    @Test
    void updatePassword() {
        String password = "2222";
        String newPassword = passwordEncoder.encode(password);
        userRepository.updatePasswordByUserId("01JGAKHA4H0TMVZKA20NYMZGYV",newPassword);
        log.debug(newPassword);
        log.debug("修改成功");
    }
}