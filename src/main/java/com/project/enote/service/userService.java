package com.project.enote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.project.enote.model.user;
import com.project.enote.repository.userRepoInterface;

import jakarta.servlet.http.HttpSession;

@Service
public class userService implements userServiceInterface {

    @Autowired
    private userRepoInterface userRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public user saveUser(user u) {
        u.setRole("ROLE_USER");
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        user newUser = userRepo.save(u);
        return newUser;
    }

    @Override
    public boolean existEmailCheck(String email) {
        return userRepo.existsByEmail(email);
    }
    
    public void removeSessionMsg(){
        @SuppressWarnings("null")
        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        session.removeAttribute("msg");
    }
}
