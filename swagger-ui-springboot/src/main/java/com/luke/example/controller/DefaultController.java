package com.luke.example.controller;

import com.luke.example.config.SwaggerProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class DefaultController {
    @Autowired
    SwaggerProperty property;

    @RequestMapping("/")
    @CrossOrigin(origins = "*")
    public String index(){
        return "redirect:/index.html";
    }
    @RequestMapping(value = "/links",method = RequestMethod.GET)
    public String getAllApiDocLink(){
        return String.join("\n",findAllFileNameInFolder(ApiDocController.SRC_MAIN_RESOURCES+"\\api-docs"));
    }


    private List<String> findAllFileNameInFolder(String folderPath){
        try {
            try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
                return paths.filter(Files::isRegularFile).map(file->file.getFileName().toString()).collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
