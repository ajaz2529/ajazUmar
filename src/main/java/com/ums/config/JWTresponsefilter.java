package com.ums.config;

import com.ums.entity.AppUser;
import com.ums.repository.AppUserRepository;
import com.ums.service.JWTservice;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Component
public class JWTresponsefilter extends OncePerRequestFilter {

    private JWTservice jwTservice;
    private AppUserRepository appUserRepository;

    public JWTresponsefilter(JWTservice jwTservice, AppUserRepository appUserRepository) {
        this.jwTservice = jwTservice;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenheader = request.getHeader("Authorization");
        if(tokenheader!=null && tokenheader.startsWith("Bearer ")){
            String token = tokenheader.substring(8, tokenheader.length()-1);
            String username = jwTservice.getUsername(token);
            Optional<AppUser> OpUser = appUserRepository.findByUsername(username);
            if(OpUser.isPresent()){
                AppUser appUser = OpUser.get();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(appUser, null, Collections.singleton(new SimpleGrantedAuthority(appUser.getUserRole()))) ;
                authenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request , response);



    }
}
