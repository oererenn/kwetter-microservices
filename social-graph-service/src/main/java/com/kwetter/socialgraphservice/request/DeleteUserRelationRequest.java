package com.kwetter.socialgraphservice.request;

public record DeleteUserRelationRequest(String followerId, String followingId) {
}
