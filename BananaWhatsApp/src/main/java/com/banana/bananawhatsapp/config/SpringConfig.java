package com.banana.bananawhatsapp.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.banana.bananawhatsapp.persistence", "com.banana.bananawhatsapp.service"})
@PropertySource("classpath:application.properties")
public class SpringConfig {
}
