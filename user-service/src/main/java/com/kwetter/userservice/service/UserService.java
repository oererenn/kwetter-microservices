package com.kwetter.userservice.service;

import com.kwetter.userservice.config.Producer;
import com.kwetter.userservice.entity.UserModel;
import com.kwetter.userservice.repository.UserRepository;
import com.kwetter.userservice.request.UserCreationRequest;
import com.kwetter.userservice.request.UserUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final Producer producer;

    public void createUser(UserCreationRequest request) {
        //check if userId is already in use
        if (userRepository.existsById(request.id())) {
            throw new IllegalArgumentException("User id is already in use");
        }
        if(userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if(userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        userRepository.save(UserModel.builder().id(request.id()).username(request.username()).email(request.email()).build());
    }

    public UserModel getUserByUsername(String id) {
        return userRepository.findByUsername(id).orElse(null);
    }

    //get user by id
    public UserModel getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    //update user information from @UserUpdateRequest
    public void updateUser(UserUpdateRequest request) {
        final var user = getUserById(request.id());
        if(user != null) {
            if(request.username() != null) {
                user.setUsername(request.username());
                producer.sendMessage("username-updated", request.username());
            }
            if(request.email() != null) {
                user.setEmail(request.email());
            }
            if(request.birthdate() != null) {
                user.setBirthdate(request.birthdate());
            }
            if(request.location() != null) {
                user.setLocation(request.location());
            }
            userRepository.save(user);
        }


    }
}
