package com.github.halalala222.sprintboothelloword.config;

import com.fasterxml.jackson.core.filter.FilteringGeneratorDelegate;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.github.halalala222.sprintboothelloword.handler.BaseException;
import com.github.halalala222.sprintboothelloword.handler.ResponseCode;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Slf4j
@Component
public class JwtAuthenticationFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JwtUtils jwtUtils = new JwtUtils();
        try {
            String token = jwtUtils.getTokenFromRequestHeader(request);
            if (jwtUtils.validateToken(token)) {
                return true;
            }
        } catch (BaseException e) {
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("message", e.getMessage());
            responseData.put("data", null);
            responseData.put("code", e.getCode());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getOutputStream().write(responseData.toString().getBytes(StandardCharsets.UTF_8));
            return false;
        }
        return false;
    }

}
