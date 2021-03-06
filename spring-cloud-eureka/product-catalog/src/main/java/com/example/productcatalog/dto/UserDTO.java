package com.example.productcatalog.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class UserDTO extends BaseEntityDTO<Long> {
    private static final String SPACE = " ";

    protected Long id;

    /**
     * emailId is treated as userName
     */

    protected String emailId;

    protected String password;

    protected String firstName;

    protected String lastName;

    protected String phoneNumber;

    protected String alternatePhoneNumber;

    protected String gender;

    protected Date dateOfBirth;

    protected MultipartFile profilePic;

    protected boolean accountExpired = false;

    protected boolean accountLocked = false;

    protected boolean credentialsExpired = false;

    protected boolean accountEnabled = true;

    public String getUsername() {
        return this.emailId;
    }

    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    public boolean isEnabled() {
        return accountEnabled;
    }

    public String getFullName() {
        return this.firstName + SPACE + this.lastName;
    }

}

