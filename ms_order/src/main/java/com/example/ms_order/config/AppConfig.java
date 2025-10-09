package com.example.ms_order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    
    @Bean //Crie este objeto RestTemplate e o mantenha pronto 
          //para ser usado em qualquer outra parte da aplicação
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
