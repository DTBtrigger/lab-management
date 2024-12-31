package org.example.labmanagement.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.labmanagement.component.JWTComponent;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.exception.Code;
import org.example.labmanagement.service.UserService;
import org.example.labmanagement.vo.ResultVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTComponent jwtComponent;

    @PostMapping("login")
    public ResultVO login(@RequestBody User user,HttpServletResponse response) {
        User userR = userService.findUserByAccount(user.getAccount());
        log.debug("{}{}",user.getAccount(),user.getPassword());
        if (userR == null) {
            return ResultVO.error(Code.LOGIN_NOTEXIST);
        } else if (!passwordEncoder.matches(user.getPassword(),userR.getPassword())) {
            return ResultVO.error(Code.LOGIN_ERROR);
        }else {
            String token = jwtComponent.encode(Map.of("uid",userR.getId(),"role",userR.getRole(),"account",userR.getAccount()));
            response.addHeader("token",token);
            response.addHeader("role",userR.getRole());
            response.addHeader("account",userR.getAccount());
            return ResultVO.succuss(userR);
        }
    }

}
