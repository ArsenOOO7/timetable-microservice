package com.arsen.identity.management.filter;

import com.arsen.common.security.JWTUtils;
import com.arsen.identity.management.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtAuthenticationVerifierFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Value("${jwt.token.starts}")
    private String tokenStart;

    @Value("${jwt.token.secret}")
    private String secret;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String emailHeader = request.getHeader("email");
        String roleHeader = request.getHeader("role");

        if(header != null && header.startsWith(tokenStart)){
            Map<String, String> data = new HashMap<>();

            data.put("token", header.substring(tokenStart.length() + 1));
            data.put("secret", secret);
            data.put("emailHeader", emailHeader);
            data.put("roleHeader", roleHeader);

            JWTUtils.extractData(data, this::authenticateUser, authService::validToken);
        }

        filterChain.doFilter(request, response);
    }


    private void authenticateUser(String email, String role){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, "", List.of(new SimpleGrantedAuthority(role)));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
