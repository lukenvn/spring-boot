package com.luke.example;

import com.luke.example.controller.ForwardServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {
    @Bean
    public ServletRegistrationBean proxyServletRegistrationBean() {

        ServletRegistrationBean bean = new ServletRegistrationBean(
                new ForwardServlet(), "/m/*");
        bean.setLoadOnStartup(1);
//        bean.addInitParameter("targetUri", properties.getTargetUrl());
        return bean;
    }

    @Bean
    WebMvcConfigurer configurer () {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addResourceHandlers (ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/api-doc/**").
                        addResourceLocations("classpath:/api-docs/");
            }
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
