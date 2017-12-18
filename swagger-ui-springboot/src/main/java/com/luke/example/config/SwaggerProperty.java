package com.luke.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix="swagger.configuration")
public class SwaggerProperty {
    private List<Property> properties ;

    public List<Property> getProperties() {
        return properties;
    }

    public SwaggerProperty setProperties(List<Property> properties) {
        this.properties = properties;
        return this;
    }

   public static class Property{
        private String name;
        private String url;
        private String version;

        public String getName() {
            return name;
        }

        public Property setName(String name) {
            this.name = name;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Property setUrl(String url) {
            this.url = url;
            return this;
        }

        public String getVersion() {
            return version;
        }

        public Property setVersion(String version) {
            this.version = version;
            return this;
        }
    }
}