package com.demo.server_oauth2_intk.rest;

import com.demo.server_oauth2_intk.model.request.OAuth2TokenRequest;
import com.demo.server_oauth2_intk.model.response.OAuth2TokenResponse;
import reactor.core.publisher.Mono;

public interface IOAuthTokenRest {
    Mono<OAuth2TokenResponse> getToken(OAuth2TokenRequest request);
}
