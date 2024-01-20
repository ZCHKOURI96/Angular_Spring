package com.gestion.formation.controller;

import org.springframework.security.access.annotation.Secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.formation.dto.LoginDTO;
import com.gestion.formation.dto.SignUpDTO;
import com.gestion.formation.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@Secured("ADMIN")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDto){
        String response = authService.authenticateUser(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDto, String userType){
        String response = authService.registerUser(signUpDto, userType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
