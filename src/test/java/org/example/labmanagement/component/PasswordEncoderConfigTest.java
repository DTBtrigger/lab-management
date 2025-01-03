package org.example.labmanagement.component;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PasswordEncoderConfigTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void bcryptEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "3456";
        String encodePassword = encoder.encode(password);
        log.debug("{}",encodePassword);
    }

    @Test
    void match() {
        String password = "3456";
        String encode = "$2a$10$2/QvSfWg97q0ZJPNRHHz9O/2WGDpQHhz7PrCArlD9P.ZllKmATMJ.";
        log.debug("{}",passwordEncoder.matches(password,encode));
    }
}