package com.demo.server_oauth2_intk.repository;

import com.demo.server_oauth2_intk.document.ClientDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends MongoRepository<ClientDocument, String> {

    Optional<ClientDocument> findByClientId(String clientId);
}
