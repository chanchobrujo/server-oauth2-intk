package com.demo.server_oauth2_intk.controller;

import com.demo.server_oauth2_intk.model.request.OAuth2TokenRequest;
import com.demo.server_oauth2_intk.model.request.UserRequest;
import com.demo.server_oauth2_intk.model.response.OAuth2TokenResponse;
import com.demo.server_oauth2_intk.model.response.UserResponse;
import com.demo.server_oauth2_intk.rest.IOAuthTokenRest;
import com.demo.server_oauth2_intk.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;
    private final IOAuthTokenRest oAuthTokenRest;

    @PostMapping
    public ResponseEntity<Mono<OAuth2TokenResponse>> getToken(@RequestHeader Map<String, String> headers) {
        return ofNullable(headers)
                .filter(h -> h.containsKey("code"))
                .map(h -> h.get("code"))
                .map(OAuth2TokenRequest::new)
                .map(this.oAuthTokenRest::getToken)
                .map(r -> status(ACCEPTED).body(r))
                .orElseThrow();
    }

    @PostMapping("/create-customer")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        return status(CREATED).body(this.userService.save(request, false));
    }
}
