package com.example.rest.webservices.user;

import com.example.rest.webservices.user.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {


    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private UserRepository userRepository;


    //retrieve all user
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        List<User> users = userRepository.findAll();
        if (users == null || users.isEmpty()) {
            throw new UserNotFoundException("Users not found");
        }

        return users;
    }

    //retrieve one user (id)
    @GetMapping("/jpa/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("id-"+id);
        }
        return user.get();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);

        URI location  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);

    }

}
