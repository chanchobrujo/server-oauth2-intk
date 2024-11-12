package com.demo.server_oauth2_intk.repository;

import com.demo.server_oauth2_intk.document.RoleDocument;
import com.demo.server_oauth2_intk.enums.RoleName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends MongoRepository<RoleDocument, String> {
    Optional<RoleDocument> findByRole(RoleName roleName);
}
