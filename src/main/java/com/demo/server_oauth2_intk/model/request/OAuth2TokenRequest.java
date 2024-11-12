package com.demo.server_oauth2_intk.model.request;

import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
public class OAuth2TokenRequest {
    private String code;
    private String code_verifier;

    private String clientId;
    private String grantType;
    private String redirectUri;

    public OAuth2TokenRequest(String code) {
        this.code = code;
    }

    public MultiValueMap<String, String> toFromData() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", this.getCode());
        formData.add("client_id", this.getClientId());
        formData.add("grant_type", this.getGrantType());
        formData.add("redirect_uri", this.getRedirectUri());
        formData.add("code_verifier", this.getCode_verifier());
        return formData;
    }
}
