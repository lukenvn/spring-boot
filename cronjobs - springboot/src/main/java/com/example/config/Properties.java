package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by nvnhung on 05/26/2017.
 */
@Configuration
public class Properties {

    @Value("${cron.expression}")
    private String checkTimeCron;

    public String getCheckTimeCron() {
        return checkTimeCron;
    }

}
