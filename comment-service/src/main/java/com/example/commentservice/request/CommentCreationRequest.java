package com.example.commentservice.request;

public record CommentCreationRequest(String content, String kweetId, String userId) {
}
