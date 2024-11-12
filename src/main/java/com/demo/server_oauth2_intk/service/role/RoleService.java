package com.demo.server_oauth2_intk.service.role;

import com.demo.server_oauth2_intk.document.RoleDocument;
import com.demo.server_oauth2_intk.enums.RoleName;
import com.demo.server_oauth2_intk.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final IRoleRepository roleRepository;

    @Override
    public void save(RoleName roleName) {
        this.roleRepository.save(new RoleDocument(roleName));
    }

    @Override
    public RoleDocument find(String roleName) {
        return this.roleRepository
                .findByRole(RoleName.valueOf(roleName))
                .stream()
                .findFirst()
                .orElseThrow();
    }

    @Override
    public int count() {
        return (int) this.roleRepository.count();
    }
}
