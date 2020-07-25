package com.example.crudJwtTests.dto;

import com.example.crudJwtTests.domain.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class UserNewDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Name is required!")
    @Length(min = 10, max = 80, message = "Limit")
    private String name;
    @NotEmpty(message = "Email is required!")
    @Length(min = 10, max = 80, message = "Limit")
    @Email(message = "Email invalid")
    private String email;
    @NotEmpty(message = "Password is required!")
    private String password;

    public UserNewDTO() {

    }

    public UserNewDTO(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
