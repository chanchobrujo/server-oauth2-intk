package com.demo.server_oauth2_intk.repository;

import com.demo.server_oauth2_intk.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findByEmail(String email);

    Optional<UserDocument> findByUsername(String username);

    boolean existsByEmailOrDocumentTypeOrDocumentNumber(String email, String documentType, String documentNumber);
}
