package com.blog.myblogsystem.filter;

import com.blog.myblogsystem.pojo.dto.UserCustomDetail;
import com.blog.myblogsystem.pojo.dto.UserLoginDTO;
import com.blog.myblogsystem.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if(token == null || Objects.equals(token, "")){
            filterChain.doFilter(request,response);
            return;
        }
        log.info("token值："+token);
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            throw new RuntimeException("token错误");
        }
        String id = claims.getSubject();
/*        UserCustomDetail userCustomDetail = new UserCustomDetail(new UserLoginDTO(Integer.parseInt(id)), null);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userCustomDetail, null, userCustomDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);*/
        filterChain.doFilter(request,response);
    }
}
