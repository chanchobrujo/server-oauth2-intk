package com.demo.server_oauth2_intk.service.user;

import com.demo.server_oauth2_intk.document.RoleDocument;
import com.demo.server_oauth2_intk.document.UserDocument;
import com.demo.server_oauth2_intk.enums.RoleName;
import com.demo.server_oauth2_intk.model.request.UserRequest;
import com.demo.server_oauth2_intk.model.response.UserResponse;
import com.demo.server_oauth2_intk.repository.IUserRepository;
import com.demo.server_oauth2_intk.service.role.IRoleService;
import com.stater.intk.common.utils.GeneralUtils;
import com.stater.intk.model.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IRoleService roleService;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse save(UserRequest request, boolean isAdmin) {
        if (this.findUser(request.email(), request.documentType(), request.documentNumber())) {
            throw new BusinessException("Usuario ya registrado.");
        }

        String userName = request.firstName().substring(0, 4) + request.lastName().substring(0, 4);
        userName = userName
                .concat(GeneralUtils.generateId(4, false))
                .toLowerCase();

        List<RoleDocument> roles = new ArrayList<>();
        roles.add(this.roleService.find(RoleName.ROLE_USER.name()));
        if (isAdmin) roles.add(this.roleService.find(RoleName.ROLE_ADMIN.name()));

        UserDocument user = request.toDocument();
        user.setRoles(roles);
        user.setUsername(userName);
        user.setPassword(this.passwordEncoder.encode(request.password()));
        user = this.userRepository.save(user);

        return user.toResponse();
    }

    @Override
    public boolean findUser(String email, String documentType, String documentNumber) {
        var flag = this.userRepository.existsByEmailOrDocumentTypeOrDocumentNumber(email, documentType, documentNumber);
        if (flag) throw new BusinessException("Usuario ya registrado.");
        return false;
    }
}
