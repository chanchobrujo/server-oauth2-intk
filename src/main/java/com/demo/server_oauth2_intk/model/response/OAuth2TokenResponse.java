package com.demo.server_oauth2_intk.model.response;

import lombok.Data;

@Data
public class OAuth2TokenResponse {
    private String access_token;
    private String refresh_token;
    private String scope;
    private String id_token;
    private String token_type;
    private Long expires_in;
}
