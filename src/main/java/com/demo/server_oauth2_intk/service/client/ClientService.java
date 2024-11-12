package com.demo.server_oauth2_intk.service.client;

import com.demo.server_oauth2_intk.document.ClientDocument;
import com.demo.server_oauth2_intk.model.request.ClientRequest;
import com.demo.server_oauth2_intk.repository.IClientRepository;
import com.stater.intk.common.utils.MapperUtils;
import com.stater.intk.model.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private final IClientRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(ClientRequest request) {
        ClientDocument client = MapperUtils.objectToObject(request, ClientDocument.class);
        var secret = client.getClientSecret();
        secret = this.passwordEncoder.encode(secret);
        client.setClientSecret(secret);
        this.repository.save(client);
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        log.info("");
    }

    @Override
    public RegisteredClient findById(String id) {
        return this.repository.findById(id)
                .map(ClientDocument::toRegisteredClient)
                .orElseThrow(() -> new BusinessException("Client id no definido."));
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return this.repository.findByClientId(clientId)
                .map(ClientDocument::toRegisteredClient)
                .orElseThrow(() -> new BusinessException("Client id no definido."));
    }
}
