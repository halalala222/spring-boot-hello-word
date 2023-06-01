package com.github.halalala222.sprintboothelloword.config;

import com.github.halalala222.sprintboothelloword.handler.BaseException;
import com.github.halalala222.sprintboothelloword.utils.JwtUtils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

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
            responseData.put("data", "");
            responseData.put("code", e.getCode().getCode());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            Gson gson = new Gson();
            String responseStringData = gson.toJson(responseData);
            response.getOutputStream().write(responseStringData.getBytes(StandardCharsets.UTF_8));
            return false;
        }
        return false;
    }

}
