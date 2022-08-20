package com.isaiah.eduservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: IsaiahLu
 * @date: 2022/8/20 16:25
 */
@Configuration
public class RestTemplateConfig {
    public RestTemplateConfig() {
    }

    @Bean
    public RestTemplate resTemplate() {
        return new RestTemplate();
    }
}
