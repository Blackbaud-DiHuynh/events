package com.blackbaud.events.config;

import com.blackbaud.boot.config.CommonJerseyConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JerseyConfig extends CommonJerseyConfig {

    @PostConstruct
    public void initialize() {
        super.initialize();
        packages("com.blackbaud.events.config");
        packages("com.blackbaud.events.resources");
    }

}