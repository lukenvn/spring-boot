package com.luke.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
@EnableAutoConfiguration
public class Swagger2Configuration implements SwaggerResourcesProvider {

    @Autowired
    private SwaggerProperty config;
    @Override
    public List<SwaggerResource> get() {
        return config.getProperties().stream().map(property -> swaggerResource(property)).collect(Collectors.toList());
    }

    private SwaggerResource swaggerResource(SwaggerProperty.Property property) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(property.getName());
        swaggerResource.setLocation(property.getUrl());
        swaggerResource.setSwaggerVersion(property.getVersion());
        return swaggerResource;
    }
}
