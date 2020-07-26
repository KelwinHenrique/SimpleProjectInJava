package com.example.crudJwtTests.controller;

import com.example.crudJwtTests.domain.User;
import com.example.crudJwtTests.dto.UserDTO;
import com.example.crudJwtTests.dto.UserNewDTO;
import com.example.crudJwtTests.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findUserById(@PathVariable Integer id) {
        User user = userService.findById(id);
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok().body(userDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@Valid @RequestBody UserNewDTO userNewDTO) {
        User user = userService.fromNewDTO(userNewDTO);
        user = userService.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer id) {
        User user = userService.fromDTO(userDTO);
        user.setId(id);
        user = userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<UserDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                  @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                                  @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<User> users = userService.findPage(page, linesPerPage, orderBy, direction);
        Page<UserDTO> usersDTO = users.map(user -> new UserDTO(user));
        return ResponseEntity.ok().body(usersDTO);
    }
}
