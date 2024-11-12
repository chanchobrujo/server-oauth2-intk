package com.demo.server_oauth2_intk.rest;

import com.demo.server_oauth2_intk.model.request.OAuth2TokenRequest;
import com.demo.server_oauth2_intk.model.response.OAuth2TokenResponse;
import com.demo.server_oauth2_intk.properties.ClientIdProperties;
import com.demo.server_oauth2_intk.properties.MicroserviceProperties;
import com.demo.server_oauth2_intk.service.client.IClientService;
import com.stater.intk.model.exception.BusinessException;
import com.stater.intk.rest._WebClientRest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthTokenRest extends _WebClientRest implements IOAuthTokenRest {
    private final IClientService clientService;
    private final ClientIdProperties clientIdProperties;
    private final MicroserviceProperties microserviceProperties;

    @Override
    public Mono<OAuth2TokenResponse> getToken(OAuth2TokenRequest request) {
        var clientId = this.clientIdProperties.getClientId();
        var consumerClient = this.clientService.findByClientId(clientId);
        if (isNull(consumerClient)) throw new BusinessException("Client id no definido.");

        request.setClientId(clientId);
        request.setGrantType("authorization_code");
        request.setCode_verifier(this.clientIdProperties.getCodeVerifier());
        ofNullable(consumerClient.getRedirectUris())
                .filter(c -> !c.isEmpty())
                .map(Collection::stream)
                .map(Stream::findFirst)
                .map(c -> c.orElse(""))
                .ifPresent(request::setRedirectUri);

        var auth = this.clientIdProperties.encryptBase64Client();
        var url = this.microserviceProperties
                .getOauth2()
                .concat("/oauth2/token");

        return this.defaultGenericWebClient("POST", url, request.toFromData(), auth)
                .bodyToMono(OAuth2TokenResponse.class);
    }
}
