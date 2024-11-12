package com.demo.server_oauth2_intk.data;

import com.demo.server_oauth2_intk.model.request.ClientRequest;
import com.demo.server_oauth2_intk.properties.ClientIdProperties;
import com.demo.server_oauth2_intk.properties.UserAdminProperties;
import com.demo.server_oauth2_intk.service.client.IClientService;
import com.demo.server_oauth2_intk.service.role.IRoleService;
import com.demo.server_oauth2_intk.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.demo.server_oauth2_intk.enums.RoleName.values;
import static java.util.stream.Stream.of;
import static org.springframework.security.oauth2.core.AuthorizationGrantType.*;
import static org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
import static org.springframework.security.oauth2.core.oidc.OidcScopes.OPENID;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {
    private final IRoleService roleService;
    private final IUserService userService;
    private final IClientService clientService;

    private final ClientIdProperties clientIdProperties;
    private final UserAdminProperties userAdminProperties;

    @Override
    public void run(String... args) {
        var clientId = this.clientIdProperties.getClientId();
        if (this.roleService.count() == 0) of(values()).forEach(this.roleService::save);

        try {
            var request = this.userAdminProperties.getUserAdminRequest();
            this.userService.save(request, true);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        try {
            this.clientService.findByClientId(clientId);
        } catch (Exception e) {
            var redirectUrl = this.clientIdProperties.getRedirectUrl();
            var clientSecret = this.clientIdProperties.getClientSecret();

            Set<AuthorizationGrantType> authorizationGrantTypes = new HashSet<>();
            authorizationGrantTypes.add(REFRESH_TOKEN);
            authorizationGrantTypes.add(CLIENT_CREDENTIALS);
            authorizationGrantTypes.add(AUTHORIZATION_CODE);
            Set<ClientAuthenticationMethod> authenticationMethods = Set.of(CLIENT_SECRET_BASIC);

            ClientRequest clientRequest = new ClientRequest(clientId, clientSecret, authenticationMethods, authorizationGrantTypes, Set.of(redirectUrl), Set.of(OPENID), true);
            this.clientService.create(clientRequest);
        }
    }
}
