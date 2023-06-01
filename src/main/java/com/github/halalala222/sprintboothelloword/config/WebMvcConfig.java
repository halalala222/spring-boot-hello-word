package com.github.halalala222.sprintboothelloword.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 设置接口只有携带token才可以访问的路劲
        registry.addInterceptor(jwtAuthenticationFilter).
                addPathPatterns("/**").
                excludePathPatterns(List.of("/login", "/register"));
    }
}
