package com.example.commentservice.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.commentservice.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CosmosRepository<Comment, String> {
    List<Comment> findAllByKweetId(String kweetId);
}
