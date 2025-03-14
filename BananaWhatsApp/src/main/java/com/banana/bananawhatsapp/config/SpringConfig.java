package com.banana.bananawhatsapp.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.banana.bananawhatsapp.persistencia", "com.banana.bananawhatsapp.servicios"})
@PropertySource("classpath:application.properties")
@EntityScan("com.banana.bananawhatsapp.modelos")
@EnableJpaRepositories
public class SpringConfig {
}
