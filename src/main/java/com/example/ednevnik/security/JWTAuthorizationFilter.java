package com.example.ednevnik.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.ednevnik.configuration.ApplicationProperties;
import com.example.ednevnik.model.teacher.Teacher;
import com.example.ednevnik.service.teacher.TeacherService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final ApplicationProperties appProperties;
    private final TeacherService teacherService;

    public JWTAuthorizationFilter(AuthenticationManager authManager, ApplicationProperties appProperties, TeacherService teacherService) {
        super(authManager);
        this.appProperties = appProperties;
        this.teacherService = teacherService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(appProperties.getTokenHeader());

        if (header == null || !header.startsWith(appProperties.getTokenPrefix())) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(appProperties.getTokenHeader());
        if (token != null) {
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(appProperties.getJwtSecret().getBytes()))
                    .build()
                    .verify(token.replace(appProperties.getTokenPrefix(), ""))
                    .getSubject();

            if (user != null) {

                Teacher loggedInUser = teacherService.getTeacherByUsername(user);
                return new UsernamePasswordAuthenticationToken(loggedInUser, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}