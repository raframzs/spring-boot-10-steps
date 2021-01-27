package com.RRS.springbootin10steps.controller;

import com.RRS.springbootin10steps.exception.UserNotFoundException;
import com.RRS.springbootin10steps.model.User;
import com.RRS.springbootin10steps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public List<User> getAll() {
        return userService.findAll();
    }

    /**
     * GET a desired user by the id
     * @param id The user serched
     * @return a User
     */
    @GetMapping(path = "/users/{id}")
    public User findByID(@PathVariable Long id){
        User user = userService.findById(id);
        if (user == null){
            throw new UserNotFoundException("id-" + id);
        }
        return user;
    }

    /**
     * POST a new user element
     * @param user the user to persist
     * @return 201 Status code if the creation was successful
     */
    @PostMapping(path = "/users")
    public ResponseEntity<Object> save(@RequestBody User user){
        User savedUser = userService.save(user);

//      This will return the current request URI
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).build();

    }



}
