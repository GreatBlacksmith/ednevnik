package com.example.ednevnik.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
@Data
public class ApplicationProperties {

    @Value("${jwt.authentication.secret}")
    private String jwtSecret;

    @Value("${jwt.authentication.validity}")
    private String tokenValidity;

    @Value("${jwt.authentication.token-prefix}")
    private String tokenPrefix;

    @Value("${jwt.authentication.header-string}")
    private String tokenHeader;
}
