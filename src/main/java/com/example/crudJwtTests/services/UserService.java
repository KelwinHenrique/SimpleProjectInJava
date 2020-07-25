package com.example.crudJwtTests.services;

import com.example.crudJwtTests.domain.User;
import com.example.crudJwtTests.dto.UserDTO;
import com.example.crudJwtTests.dto.UserNewDTO;
import com.example.crudJwtTests.repositories.UserRepository;
import com.example.crudJwtTests.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(
                "User not found! Id: " + id + ", Type: " + User.class.getName()));
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        findById(user.getId());
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return userRepository.findAll(pageRequest);
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getEmail(), null);
    }

    public User fromNewDTO(UserNewDTO userNewDTO) {
        return new User(userNewDTO.getName(), userNewDTO.getEmail(), passwordEncoder.encode(userNewDTO.getPassword()));
    }
}
