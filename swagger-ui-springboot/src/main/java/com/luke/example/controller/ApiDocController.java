package com.luke.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by nvnhung on 12/19/2017.
 */
@RestController
@RequestMapping("/api-doc")
public class ApiDocController {

    public static final String SRC_MAIN_RESOURCES = "src\\main\\resources\\";

    @RequestMapping(value = "/links",method = RequestMethod.GET)
    public String getAllApiDocLink(){
        return String.join(",",findAllFileNameInFolder("api-docs"));
    }


    private List<String> findAllFileNameInFolder(String folderPath){
        try {
            try (Stream<Path> paths = Files.walk(Paths.get("src/main/resources/api-docs"))) {
                return paths.filter(Files::isRegularFile).map(file->file.getFileName().toString()).collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
//
//    @RequestMapping(value = "/ClientOnboardingInterfaceV7.json",produces = "application/json",method = RequestMethod.GET)
//    public String getClientOnboardingInterface() throws IOException {
//        return convertFileToJson("ClientOnboardingInterfaceV7.json");
//    }
//
//    @RequestMapping(value = "/LoanAdvisoryInterfaceV1.0.1.json",produces = "application/json",method = RequestMethod.GET)
//    public String getLoanAdvisoryInterface() throws IOException {
//        return convertFileToJson("LoanAdvisoryInterfaceV1.0.1.json");
//    }
//
//    @RequestMapping(value = "/LoanOrderInterfaceV1.json",produces = "application/json",method = RequestMethod.GET)
//    public String getLoanOrderInterface() throws IOException {
//        return convertFileToJson("LoanOrderInterfaceV1.json");
//    }
//
//    @RequestMapping(value = "/RealEstateInterfaceV1.0.json",produces = "application/json",method = RequestMethod.GET)
//    public String getRealEstateInterfaceV() throws IOException {
//        return convertFileToJson("RealEstateInterfaceV1.0.json");
//    }
//
//    @RequestMapping(value = "/fil-bsa-axon-loan-advisory-0.0.8.yaml",produces = "application/json",method = RequestMethod.GET)
//    public String getFILBSAAxonLoanAdvisory() throws IOException {
//        return convertFileToJson("fil-bsa-axon-loan-advisory-0.0.8.yaml");
//    }

    private String convertFileToJson(String fileName) throws IOException {
        List<String> strings = Files.readAllLines(Paths.get(SRC_MAIN_RESOURCES + fileName));
        return String.join("\n", strings);
    }

}
