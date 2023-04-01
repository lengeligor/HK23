package com.pegasus.hk.config;


import com.pegasus.hk.client.BotClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {return new RestTemplate();}

    @Bean
    public BotClient botClient(){return new BotClient(new RestTemplate());}
}
