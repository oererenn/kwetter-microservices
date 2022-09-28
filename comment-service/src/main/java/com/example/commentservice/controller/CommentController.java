package com.example.commentservice.controller;

import com.example.commentservice.entity.Comment;
import com.example.commentservice.request.CommentCreationRequest;
import com.example.commentservice.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public void addComment(@RequestBody CommentCreationRequest request) {
        commentService.addComment(request);
    }

    @GetMapping(value = "/{id}")
    public List<Comment> getComments(@PathVariable String id) {
        return commentService.getComments(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
    }

    @PutMapping(value = "/{id}")
    public void likeComment(@PathVariable String id) {
        commentService.likeComment(id);
    }
}
