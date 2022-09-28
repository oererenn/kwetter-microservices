package com.kwetter.kweetservice.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.kwetter.kweetservice.entity.Kweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KweetRepository extends MongoRepository<Kweet, String> {

    List<Kweet> findByUserId(String userId);

    List<Kweet> findByUsername(String username);
}
