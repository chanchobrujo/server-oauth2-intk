package com.demo.server_oauth2_intk.service.client;

import com.demo.server_oauth2_intk.model.request.ClientRequest;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

public interface IClientService extends RegisteredClientRepository {
    void create(ClientRequest request);
}
