package com.demo.server_oauth2_intk.document;

import com.demo.server_oauth2_intk.enums.RoleName;
import com.stater.intk.common.utils.GeneralUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDocument implements GrantedAuthority {
    @Id
    private String id = GeneralUtils.generateId(3, false);
    private RoleName role;

    public RoleDocument(RoleName role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }
}
