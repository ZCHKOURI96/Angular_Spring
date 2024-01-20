package com.gestion.formation.service;

import com.gestion.formation.dto.LoginDTO;
import com.gestion.formation.dto.SignUpDTO;
import com.gestion.formation.entity.Admin;
import com.gestion.formation.entity.Assistant;
import com.gestion.formation.entity.Formateur;
import com.gestion.formation.entity.Role;
import com.gestion.formation.entity.User;
import com.gestion.formation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gestion.formation.repository.UserRepository;
import com.gestion.formation.repository.RoleRepository;

import java.util.Collections;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUser(LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User signed-in successfully!";
    }

    public String registerUser(SignUpDTO signUpDto, String userType) {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return "Username is already taken!";
        }

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return "Email is already taken!";
        }

        User user;
        Role role;

        if("ADMIN".equals(userType)) {
            role = roleRepository.findByName("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("Role not found!"));
            user= new Admin();
        } else if ("FORMATEUR".equals(userType)) {
            role = roleRepository.findByName("ROLE_FORMATEUR").orElseThrow(() -> new RuntimeException("Role not found!"));
            user = new Formateur();
        } else if ("ASSISTANT".equals(userType)) {
            role = roleRepository.findByName("ROLE_ASSISTANT").orElseThrow(() -> new RuntimeException("Role not found!"));
            user = new Assistant();
        } else {
            return "Invalid user type!";
        }

        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRoles(Collections.singleton(role));

        userRepository.save(user);

        return "User registered successfully";
    }
}