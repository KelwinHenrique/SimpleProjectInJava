package com.example.crudJwtTests.dto;

import com.example.crudJwtTests.domain.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private Integer id;
    private String name;
    private String email;

    public UserDTO() {

    }

    public UserDTO(User user) {
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
}
