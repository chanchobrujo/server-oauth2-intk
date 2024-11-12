package com.demo.server_oauth2_intk.properties;

import com.demo.server_oauth2_intk.model.request.UserRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class UserAdminProperties {
    @Value("${user-admin.lastName}")
    private String lastName;
    @Value("${user-admin.firstName}")
    private String firstName;
    @Value("${user-admin.documentType}")
    private String documentType;
    @Value("${user-admin.documentNumber}")
    private String documentNumber;
    @Value("${user-admin.password}")
    private String password;
    @Value("${user-admin.numberPhone}")
    private String numberPhone;
    @Value("${user-admin.email}")
    private String email;

    public UserRequest getUserAdminRequest() {
        var email = this.getEmail();
        var lastName = this.getLastName();
        var password = this.getPassword();
        var firstName = this.getFirstName();
        var numberPhone = this.getNumberPhone();
        var documentType = this.getDocumentType();
        var documentNumber = this.getDocumentNumber();
        return new UserRequest(email, password, lastName, firstName, numberPhone, documentType, documentNumber);
    }
}
