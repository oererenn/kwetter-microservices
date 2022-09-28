package com.kwetter.kweetservice.request;

public record KweetCreationRequest(
        String userId,
        String username,
        String content) {
}
