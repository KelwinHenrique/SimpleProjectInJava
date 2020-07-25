package com.example.crudJwtTests.services;

import com.example.crudJwtTests.domain.User;
import com.example.crudJwtTests.dto.UserNewDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void createUser() {
        User user = new User("Kelwin", "kelwin@mail.com", "kelwin");
        UserNewDTO userNewDTO = new UserNewDTO(user);
        user = userService.fromNewDTO(userNewDTO);
        User savedUser = userService.create(user);
        Assertions.assertEquals(user.getName(), savedUser.getName());
        Assertions.assertEquals(user.getEmail(), savedUser.getEmail());
        Assertions.assertEquals(user.getPassword(), savedUser.getPassword());
        Assertions.assertNotNull(savedUser.getId());
    }
}
