package com.project.enote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.enote.model.user;
import com.project.enote.service.userService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

@Autowired
private userService userService;

    @GetMapping("/")
    public String index(){
        System.out.println("index Page");
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        System.out.println("register Page");
        return "register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute user u, HttpSession session){
        System.out.println(u.toString());
        boolean b = userService.existEmailCheck(u.getEmail());
        if(b){
            session.setAttribute("msg","User already exist, try to Login!");
        }else{
            user saveUser = userService.saveUser(u);
            if (saveUser!=null) {
                session.setAttribute("msg", "Register Sucessfully.");
            }else{
                session.setAttribute("msg", "Something wrong on server.");
            }
        }
        return "redirect:/register";
    }

    @GetMapping("/signin")
    public String login(){
        System.out.println("login Page");
        return "login";
    }
}