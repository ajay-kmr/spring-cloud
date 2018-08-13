package com.example.productcatalog.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity(name = "globo_mart_user")
public class User extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @NotEmpty
    protected String emailId;

    @NotEmpty
    protected String password;

    protected String firstName;

    protected String lastName;

    protected String phoneNumber;

    protected String alternatePhoneNumber;

    protected String gender;

    protected Date dateOfBirth;

    protected String profileUrl;

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

}

