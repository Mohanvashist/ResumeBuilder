package com.ResumeBuilder.ResumeBuilder.controller;

import com.ResumeBuilder.ResumeBuilder.jwt.JwtTokeProvider;
import com.ResumeBuilder.ResumeBuilder.model.Role;
import com.ResumeBuilder.ResumeBuilder.model.StringResponse;
import com.ResumeBuilder.ResumeBuilder.model.User;
import com.ResumeBuilder.ResumeBuilder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    JwtTokeProvider jwtTokeProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/api/user/registration")
    public ResponseEntity<?> register(@RequestBody User user){
        if(userService.findByUsername((user.getUsername()))!= null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }

    @GetMapping("/api/user/login")
    public ResponseEntity<?> getUser(Principal principal){
        if(principal==null){
            return ResponseEntity.ok(principal);
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) principal;

        User user = userService.findByUsername(authenticationToken.getName());
        Authentication authentication = authenticationToken;
        user.setToken(jwtTokeProvider.generateToken(authenticationToken));

        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
