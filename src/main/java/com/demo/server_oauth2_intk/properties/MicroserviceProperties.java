package com.demo.server_oauth2_intk.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class MicroserviceProperties {
    @Value("${spring.server.oauth2}")
    private String oauth2;
}
