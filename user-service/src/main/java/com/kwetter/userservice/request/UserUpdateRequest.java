package com.kwetter.userservice.request;

public record UserUpdateRequest(String id,String username, String email, String birthdate, String location) {
}
