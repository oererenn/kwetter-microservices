package com.example.commentservice.service;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.repository.CommentRepository;
import com.example.commentservice.request.CommentCreationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void addComment(CommentCreationRequest request) {
        final var comment = Comment.builder()
                .id(UUID.randomUUID().toString())
                .userId(request.userId())
                .content(request.content())
                .kweetId(request.kweetId())
                .build();
        commentRepository.save(comment);
    }

    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comment> getComments(String kweetId) {
        return commentRepository.findAllByKweetId(kweetId);
    }

    public void likeComment(String id) {
        final var comment = commentRepository.findById(id).orElse(null);
        assert comment != null;
        comment.setLikeCount(comment.getLikeCount() + 1);
        commentRepository.save(comment);
    }
}
