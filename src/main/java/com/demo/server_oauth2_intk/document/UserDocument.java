package com.demo.server_oauth2_intk.document;

import com.demo.server_oauth2_intk.model.response.UserResponse;
import com.stater.intk.common.utils.DateUtils;
import com.stater.intk.common.utils.GeneralUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDocument implements UserDetails {
    @Id
    private String id = GeneralUtils.generateId(20, true);
    private String email;
    private String password;
    private String username;

    private String firstName;
    private String lastName;
    private String documentType;
    private String documentNumber;

    private String numberPhone;
    private boolean locked = false;
    private boolean expired = false;
    private boolean disabled = false;
    private boolean credentialsExpired = false;
    private LocalDateTime createdDate = DateUtils.localDateTimeNow();
    private List<RoleDocument> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return !disabled;
    }

    public UserResponse toResponse() {
        return new UserResponse(this.username, this.email, this.numberPhone);
    }
}
