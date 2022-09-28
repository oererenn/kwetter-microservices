package com.kwetter.userservice.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.kwetter.userservice.entity.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository  extends MongoRepository<UserModel, String> {
    Optional<UserModel> findByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
