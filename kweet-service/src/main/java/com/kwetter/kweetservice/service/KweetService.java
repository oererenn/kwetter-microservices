package com.kwetter.kweetservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.kweetservice.config.Producer;
import com.kwetter.kweetservice.entity.Kweet;
import com.kwetter.kweetservice.repository.KweetRepository;
import com.kwetter.kweetservice.request.KweetCreationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class KweetService {

    private final KweetRepository kweetRepository;
    private Producer producer;
    private ObjectMapper objectMapper;
    
    public Kweet createKweet(KweetCreationRequest request) {
        checkKweetLength(request.content());
        final var kweet = Kweet.builder()
                .userId(request.userId())
                .username(request.username())
                .content(request.content())
                .id(UUID.randomUUID().toString())
                .build();
        kweetRepository.save(kweet);
        try {
            log.info("Sending kweet to kwetter-kafka-producer");
            producer.sendMessage("kweet-posted", objectMapper.writeValueAsString(kweet));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return kweet;
    }

    public Kweet getKweet(String id) {
        return kweetRepository.findById(id).orElse(null);
    }

    public void deleteKweet(String id) {
        kweetRepository.deleteById(id);
    }

    public void checkKweetLength(String content) {
        if (content.length() > 140) {
            throw new IllegalArgumentException();
        }
    }

    //get all kweets by userId
    public List<Kweet> getKweetsByUserId(String userId) {
        return kweetRepository.findByUserId(userId);
    }

    public void likeKweet(String id) {
        final var kweet = getKweet(id);
        kweet.setLikeCount(kweet.getLikeCount() + 1);
        kweetRepository.save(kweet);
    }

    //get all kweets by username
    public List<Kweet> getKweetsByUsername(String username) {
        return kweetRepository.findByUsername(username);
    }
}
