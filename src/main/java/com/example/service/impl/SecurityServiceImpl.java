package com.example.service.impl;

import com.example.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {


    private final AuthenticationManager authenticationManager;
//    private final @Qualifier("customUserDetailService")
//    private final UserDetailsService userDetailsService;

//
//    public SecurityServiceImpl(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

//    @Override
//    public String findLoggedInUsername() {
//        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        if (userDetails instanceof UserDetails) {
//            return ((UserDetails) userDetails).getUsername();
//        }
//        return null;
//    }
//
//    @Override
//    public void autoLogin(String email, String password) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        }
//    }
}
