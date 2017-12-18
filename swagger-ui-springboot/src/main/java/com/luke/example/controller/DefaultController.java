package com.luke.example.controller;

import com.luke.example.config.SwaggerProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    @Autowired
    SwaggerProperty property;

    @RequestMapping("/")
    public String index(){
        return "redirect:/swagger-ui.html";
    }
}
