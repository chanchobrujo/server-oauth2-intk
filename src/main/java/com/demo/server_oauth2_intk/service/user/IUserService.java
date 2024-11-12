package com.demo.server_oauth2_intk.service.user;

import com.demo.server_oauth2_intk.model.request.UserRequest;
import com.demo.server_oauth2_intk.model.response.UserResponse;

public interface IUserService {
    UserResponse save(UserRequest dto, boolean isAdmin);

    boolean findUser(String email, String documentType, String documentNumber);
}
