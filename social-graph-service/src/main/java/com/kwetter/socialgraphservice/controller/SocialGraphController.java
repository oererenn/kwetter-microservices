package com.kwetter.socialgraphservice.controller;

import com.kwetter.socialgraphservice.entity.UserRelationModel;
import com.kwetter.socialgraphservice.request.CreateUserRelationRequest;
import com.kwetter.socialgraphservice.request.DeleteUserRelationRequest;
import com.kwetter.socialgraphservice.service.SocialGraphService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/social-graph")
public class SocialGraphController {
    private final SocialGraphService socialGraphService;

    @PostMapping
    public void createRelation(@RequestBody CreateUserRelationRequest request) {
        socialGraphService.createRelation(request);
    }

    //get all relations by id
    @GetMapping("/{id}")
    public List<UserRelationModel> getAllRelations(@PathVariable String id) {
        return socialGraphService.getAllFollowers(id);
    }

    //delete relation
    @DeleteMapping
    public void deleteRelation(@RequestBody DeleteUserRelationRequest request) {
        socialGraphService.deleteRelation(request);
    }
}
