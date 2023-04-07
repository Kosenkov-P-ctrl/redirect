package com.kosenkovps.redirect.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.kosenkovps.redirect.proxy")
public class FeignConfig {
}
