package org.example.labmanagement.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.labmanagement.dox.User;
import org.example.labmanagement.exception.Code;
import org.example.labmanagement.exception.XException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {
//    这一步是在登录之后的，所以不需要再加密解密
//    private final JWTComponent jwtComponent;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getAttribute("role").equals(User.ADMIN)) {
            return true;
        }
//        这里不要new（？）
        throw XException.builder().code(Code.FORBIDDEN).build();
    }
}
