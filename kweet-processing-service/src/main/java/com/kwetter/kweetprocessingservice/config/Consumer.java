package com.kwetter.kweetprocessingservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.kweetprocessingservice.entity.KweetProcessing;
import com.kwetter.kweetprocessingservice.service.KweetProcessingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Consumer {
    private final KweetProcessingService kweetProcessingService;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "kweet-for-processing", groupId = "groupId")
    public void listen(String message) throws JsonProcessingException {
        final var kweetProcessingMessage = objectMapper.readValue(message, KweetProcessing.class);
         kweetProcessingService.pushKweet(kweetProcessingMessage);
    }
}
