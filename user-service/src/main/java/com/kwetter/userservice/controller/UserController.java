package com.kwetter.userservice.controller;

import com.kwetter.userservice.entity.UserModel;
import com.kwetter.userservice.request.UserCreationRequest;
import com.kwetter.userservice.request.UserUpdateRequest;
import com.kwetter.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody UserCreationRequest request) {
        userService.createUser(request);
    }

    @GetMapping(value = "/{id}")
    public UserModel getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    //get user id from username
    @GetMapping(value = "/username/{username}")
    public UserModel getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    //update user
    @PutMapping
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }


}
