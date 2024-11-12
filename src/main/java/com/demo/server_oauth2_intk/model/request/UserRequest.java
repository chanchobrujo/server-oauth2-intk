package com.demo.server_oauth2_intk.model.request;

import com.demo.server_oauth2_intk.document.UserDocument;

import static com.stater.intk.common.utils.MapperUtils.objectToObject;

public record UserRequest(
        String email,
        String password,
        String lastName,
        String firstName,
        String numberPhone,
        String documentType,
        String documentNumber
) {
    public UserDocument toDocument() {
        return objectToObject(this, UserDocument.class);
    }
}