package com.kwetter.socialgraphservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.socialgraphservice.entity.Kweet;
import com.kwetter.socialgraphservice.entity.KweetProcessing;
import com.kwetter.socialgraphservice.entity.UserRelationModel;
import com.kwetter.socialgraphservice.service.SocialGraphService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.StringJoiner;

@Component
@AllArgsConstructor
@Slf4j
public class Consumer {
    private final Producer producer;
    private final ObjectMapper objectMapper;

    private final SocialGraphService socialGraphService;

    @KafkaListener(topics = "kweet-posted", groupId = "groupId")
    public void listenCreateRelation(String message) throws JsonProcessingException {
        final var kweet = objectMapper.readValue(message, Kweet.class);
        final var relations = socialGraphService.getAllFollowers(kweet.getUserId());
        final var kweetProcessingMessage = KweetProcessing.builder().kweet(kweet)
                .userRelations(relations).build();

        producer.sendMessage("kweet-for-processing", objectMapper.writeValueAsString(kweetProcessingMessage));
        log.info("Sending relation information to kweet processing service for kweet with id: {}", kweet.getId());
    }
}
