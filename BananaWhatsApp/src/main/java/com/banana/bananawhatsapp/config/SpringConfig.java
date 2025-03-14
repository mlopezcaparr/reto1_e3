package com.banana.bananawhatsapp.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.banana.bananawhatsapp.persistence", "com.banana.bananawhatsapp.service"})
@PropertySource("classpath:application.properties")
@EntityScan("com.banana.bananawhatsapp.modelos")
public class SpringConfig {
}
