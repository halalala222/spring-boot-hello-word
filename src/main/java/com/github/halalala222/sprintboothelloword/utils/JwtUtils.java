package com.github.halalala222.sprintboothelloword.utils;

import com.github.halalala222.sprintboothelloword.exception.BaseException;
import com.github.halalala222.sprintboothelloword.constants.ResponseCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : halalala222
 * @version : 1.0
 */
@Configuration
@Slf4j
public class JwtUtils {

    @Value("${jwt.expire-time}")
    private int expireTime;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Long id) {
        return Jwts.
                builder().
                setId(id.toString()).
                setExpiration(new Date(System.currentTimeMillis() + expireTime * 1000L)).
                signWith(SignatureAlgorithm.HS256, secret).
                compact();
    }

    public boolean validateToken(String token) throws BaseException {
        try {
            getTokenClaims(token);
            return true;
        } catch (Exception e) {
            log.warn("Request to parse token : {} failed message : {}", token, e.getMessage());
            throw new BaseException(ResponseCode.JWT_ERROR);
        }
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = getTokenClaims(token);
        return Long.getLong(claims.getId());
    }

    private Claims getTokenClaims(String token) {
        return Jwts.
                parser().
                setSigningKey(secret).
                parseClaimsJws(token).
                getBody();
    }

    public String getTokenFromRequestHeader(HttpServletRequest request) throws BaseException {
        String authHeader = request.getHeader("Authorization");
        if (!(authHeader == null) && !authHeader.isBlank() && authHeader.startsWith("Bearer")) {
            return authHeader.substring(7);
        } else {
            throw new BaseException(ResponseCode.JWT_ERROR);
        }
    }
}
