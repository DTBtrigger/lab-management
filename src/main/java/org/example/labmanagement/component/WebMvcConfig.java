package org.example.labmanagement.component;

import lombok.RequiredArgsConstructor;
import org.example.labmanagement.interceptor.AdminInterceptor;
import org.example.labmanagement.interceptor.LabAdminInterceptor;
import org.example.labmanagement.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;
    private final AdminInterceptor adminInterceptor;
    private final LabAdminInterceptor labAdminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/admin/**");
        registry.addInterceptor(labAdminInterceptor)
                .addPathPatterns("api/labadmin/**");
    }
}
