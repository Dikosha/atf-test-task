package com.example.atf.configs;

import com.example.atf.services.impl.DefaultClientService;
import com.example.atf.services.impl.DefaultContactService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    @Qualifier("DefaultContactService")
    public DefaultContactService contactService() {
        return new DefaultContactService();
    }

    @Bean
    @Qualifier("DefaultClientService")
    public DefaultClientService clientService() {
        return new DefaultClientService();
    }
}
