package com.kwetter.socialgraphservice.request;

public record CreateUserRelationRequest(String followerId, String followingId) {
}
