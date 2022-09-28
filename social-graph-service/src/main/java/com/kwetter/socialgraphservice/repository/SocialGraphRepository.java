package com.kwetter.socialgraphservice.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.kwetter.socialgraphservice.entity.UserRelationModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface SocialGraphRepository extends MongoRepository<UserRelationModel, String> {

    List<UserRelationModel> findAllByFollowingId(String id);

    boolean existsByFollowerIdAndFollowingId(String followerId, String followingId);

    void deleteByFollowerIdAndFollowingId(String followerId, String followingId);
}
