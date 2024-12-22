package org.example.labmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;



    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User findUserByAccount(String account) {
        return userRepository.findUserByAccount(account);
    }
}
