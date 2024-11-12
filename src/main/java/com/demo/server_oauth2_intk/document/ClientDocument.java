package com.demo.server_oauth2_intk.document;

import com.stater.intk.common.utils.GeneralUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.security.oauth2.server.authorization.client.RegisteredClient.withId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDocument {
    @Id
    private String id = GeneralUtils.generateId(5, false);
    private String clientId;
    private String clientSecret;
    private boolean requireProofKey;
    private Set<String> scopes = new HashSet<>();
    private Set<String> redirectUris = new HashSet<>();
    private Set<AuthorizationGrantType> authorizationGrantTypes = new HashSet<>();
    private Set<ClientAuthenticationMethod> authenticationMethods = new HashSet<>();

    public RegisteredClient toRegisteredClient() {
        ClientSettings clientSettings = ClientSettings
                .builder()
                .requireProofKey(this.isRequireProofKey())
                .build();
        RegisteredClient registeredClient = withId(this.getClientId())
                .clientIdIssuedAt(Instant.now())
                .clientId(this.getClientId())
                .clientSecret(this.getClientSecret())
                .clientAuthenticationMethods(am -> am.addAll(this.getAuthenticationMethods()))
                .authorizationGrantTypes(agt -> agt.addAll(this.getAuthorizationGrantTypes()))
                .redirectUris(ru -> ru.addAll(this.getRedirectUris()))
                .scopes(sc -> sc.addAll(this.getScopes()))
                .clientSettings(clientSettings).build();
        return registeredClient;
    }
}
