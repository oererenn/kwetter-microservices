package com.kwetter.socialgraphservice.service;

import com.kwetter.socialgraphservice.config.Producer;
import com.kwetter.socialgraphservice.entity.UserRelationModel;
import com.kwetter.socialgraphservice.repository.SocialGraphRepository;
import com.kwetter.socialgraphservice.request.CreateUserRelationRequest;
import com.kwetter.socialgraphservice.request.DeleteUserRelationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SocialGraphService {
    private final SocialGraphRepository socialGraphRepository;
    private final Producer producer;
    public void createRelation(CreateUserRelationRequest request) {
        if(!relationExists(request.followerId(), request.followingId())) {
            final var relation = UserRelationModel.builder().followerId(request.followerId()).followingId(request.followingId()).build();
            socialGraphRepository.save(relation);
            producer.sendMessage("relation-created",request.followerId() + "," + request.followingId());
            log.info("Relation created: between {} and {}",request.followerId() + "," + request.followingId());
            return;
        }
        log.info("Relation already exists: between {} and {}", request.followerId(), request.followingId());
    }

    //delete relation
    public void deleteRelation(DeleteUserRelationRequest request) {
        if(relationExists(request.followerId(), request.followingId())) {
            socialGraphRepository.deleteByFollowerIdAndFollowingId(request.followerId(), request.followingId());
            producer.sendMessage("relation-deleted",request.followerId() + "," + request.followingId());
            log.info("Relation deleted: between {} and {}",request.followerId() + "," + request.followingId());
            return;
        }
        log.info("Relation does not exist: between {} and {}", request.followerId(), request.followingId());
    }

    //get all followers by id
    public List<UserRelationModel> getAllFollowers(String id) {
        return socialGraphRepository.findAllByFollowingId(id);
    }

    //check if relation exists
    public boolean relationExists(String followerId, String followingId) {
        return socialGraphRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }
}
