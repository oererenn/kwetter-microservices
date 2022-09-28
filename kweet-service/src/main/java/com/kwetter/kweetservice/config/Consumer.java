package com.kwetter.kweetservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.kweetservice.controller.TimelineMessage;
import com.kwetter.kweetservice.entity.Kweet;
import com.kwetter.kweetservice.service.KweetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class Consumer {
    private KweetService kweetService;
    private Producer producer;
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "relation-created", groupId = "groupId")
    public void listenCreateRelation(String message) throws JsonProcessingException {
        //split message by comma to follower and followed
        String[] split = message.split(",");
        String followerId = split[0];
        String followedId = split[1];
        log.info("Relation Created between " + followerId + " and " + followedId);
        //get all kweets from followed
        List<Kweet> kweets = kweetService.getKweetsByUserId(followedId);
        //add follower username at the end of the @kweetsString separated by comma
        final var timelineMessage = TimelineMessage.builder()
                .followerId(followerId).kweets(kweets).build();

        producer.sendMessage("new-kweets-to-follower-timeline", objectMapper.writeValueAsString(timelineMessage));
        log.info("Sending new kweets to timeline of " + followerId);
    }

    @KafkaListener(topics = "relation-deleted", groupId = "groupId")
    public void listenDeleteRelation(String message) throws JsonProcessingException {
        //split message by comma to follower and followed
        String[] split = message.split(",");
        String followerId = split[0];
        String followedId = split[1];
        log.info("Relation deleted between " + followerId + " and " + followedId);
        //get all kweets from followed
        List<Kweet> kweets = kweetService.getKweetsByUserId(followedId);
        //add follower username at the end of the @kweetsString separated by comma
        final var timelineMessage = TimelineMessage.builder()
                .followerId(followerId).kweets(kweets).build();

        producer.sendMessage("remove-kweets-from-follower-timeline", objectMapper.writeValueAsString(timelineMessage));
        log.info("Sending new kweets to timeline of " + followerId);
    }
}