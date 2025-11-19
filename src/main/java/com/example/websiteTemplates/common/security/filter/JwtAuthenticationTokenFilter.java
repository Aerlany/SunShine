package com.example.websiteTemplates.common.security.filter;

import com.example.websiteTemplates.common.security.userAuth.LoginUser;
import com.example.websiteTemplates.common.tools.JwtUtil;
import com.example.websiteTemplates.common.tools.Redis.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/*
OncePerRequestFilter作为SpringMVC中的一个过滤器，
在每次请求的时候都会执行。它是对于Filter的抽象实现。
比起特定的过滤器来说，这个过滤器对于每次的请求都进行请求过滤。
 */

/**
 * 本过滤器拦截每一个请求，判断请求发起者身份信息是否合法
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取request中的Token
        String token = request.getHeader("Token");
        // TODO 如果token不存在
        if (!StringUtils.hasText(token)) {
            //当第一次用户登陆时伟携带Token时放行
            filterChain.doFilter(request, response);
            //为了防止过滤器重复执行
            return;
        }

        // 解析Token 获取Uid
        String subject;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            subject = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("非法Token");
//            throw new AuthenticationException("非法Token");
        }
        // 从Redis中获取用户信息
        String redisKey = "sys_login_user_" + subject;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }

        // 存入SecurityContextHolder
        // TODO 从数据库中获取权限信息封装到Authentication
        // 添加的Authentication参数用于告诉Security，当前这个用户是已经验证过的
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
