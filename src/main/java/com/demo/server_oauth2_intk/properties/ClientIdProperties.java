package com.demo.server_oauth2_intk.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import static com.stater.intk.common.utils.SecurityUtils.encryptBase64;

@Getter
@Configuration
public class ClientIdProperties {
    @Value("${client.clientId}")
    private String clientId;
    @Value("${client.clientSecret}")
    private String clientSecret;
    @Value("${client.redirectUrl}")
    private String redirectUrl;
    @Value("${client.codeVerifier}")
    private String codeVerifier;

    public String encryptBase64Client() {
        return "Basic ".concat(encryptBase64(clientId.concat(":").concat(clientSecret)));
    }
}
