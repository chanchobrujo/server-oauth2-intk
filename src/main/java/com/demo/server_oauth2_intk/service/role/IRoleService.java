package com.demo.server_oauth2_intk.service.role;

import com.demo.server_oauth2_intk.document.RoleDocument;
import com.demo.server_oauth2_intk.enums.RoleName;

public interface IRoleService {
    void save(RoleName roleName);

    RoleDocument find(String roleName);

    int count();
}
