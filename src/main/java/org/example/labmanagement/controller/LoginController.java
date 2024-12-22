package org.example.labmanagement.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.exception.Code;
import org.example.labmanagement.service.UserService;
import org.example.labmanagement.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private String messqge;

    @PostMapping("login")
    public ResultVO login(@RequestBody User user) {
        User userR = userService.findUserByAccount(user.getAccount());
        if (userR == null) {
            return ResultVO.error(Code.LOGIN_NOTEXIST);
        } else if (!user.getPassword().equals(userR.getPassword())) {
            return ResultVO.error(Code.LOGIN_ERROR);
        }else {
            log.debug("{}",userR.toString());
            return ResultVO.succuss(userR);
        }
    }

}
