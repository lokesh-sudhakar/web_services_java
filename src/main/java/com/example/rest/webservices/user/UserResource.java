package com.example.rest.webservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;


    //retrieve all user
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        List<User> users = userDaoService.findAll();
        if (users == null || users.isEmpty()) {
            throw new UserNotFoundException("Users not found");
        }

        return users;
    }

    //retrieve one user (id)
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = userDaoService.findUser(id);
        if (user == null) {
            throw new UserNotFoundException("id-"+id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);

        URI location  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/delete/{id}")
    public User deleteUser(@PathVariable int id) {
        User deletedUser = userDaoService.deleteUserById(id);
        if (deletedUser == null) {
            throw new UserNotFoundException("id-"+id);
        }
        return deletedUser;
    }

}
/*
In case you are using HATEOAS v1.0 and above (Spring boot >= 2.2.0), do note that the classnames have changed. Notably the below classes have been renamed:

        ResourceSupport changed to RepresentationModel
        Resource changed to EntityModel
        Resources changed to CollectionModel
        PagedResources changed to PagedModel
        ResourceAssembler changed to RepresentationModelAssembler
        More information available in the official documentation here.*/
