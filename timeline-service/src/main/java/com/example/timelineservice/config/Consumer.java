package com.example.timelineservice.config;

import com.example.timelineservice.entity.TimelineMessage;
import com.example.timelineservice.service.TimelineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class Consumer {
    private final ObjectMapper objectMapper;
    private final TimelineService timelineService;

    @KafkaListener(topics = "new-kweets-to-follower-timeline", groupId = "groupId")
    public void addKweetsToHomeTimeLine(String message) throws JsonProcessingException {
      log.info("Received message: " + message);
      final var timelineMessage = objectMapper.readValue(message, TimelineMessage.class);
      timelineService.saveHomeTimeline(timelineMessage);
    }

    @KafkaListener(topics = "remove-kweets-from-follower-timeline", groupId = "groupId")
    public void removeKweetsFromHomeTimeLine(String message) throws JsonProcessingException {
        log.info("Received message: " + message);
        final var timelineMessage = objectMapper.readValue(message, TimelineMessage.class);
        timelineService.removeKweetsFromHomeTimeline(timelineMessage);
    }

}
