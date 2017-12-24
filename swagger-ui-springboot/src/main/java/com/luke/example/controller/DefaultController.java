//package com.luke.example.controller;
//
//import com.luke.example.config.SwaggerProperty;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@Controller
//
//@RequestMapping("/m/**")
//public class DefaultController {
//    @Autowired
//    SwaggerProperty property;
//
//    @CrossOrigin(origins = "*")
//    @GetMapping
//    public String index(HttpServletRequest req, HttpServletResponse resp){
//        try {
//            return ForwardServlet.request(req);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
