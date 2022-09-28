package com.kwetter.kweetprocessingservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kwetter.kweetprocessingservice.entity.Kweet;
import com.kwetter.kweetprocessingservice.entity.KweetProcessing;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


@Service
@AllArgsConstructor
@Slf4j
public class KweetProcessingService {
    private final RedisTemplate redisTemplate;

    private final ObjectMapper objectMapper;

    // push a new kweet to redis cache
    public void pushKweet(KweetProcessing message) throws JsonProcessingException {
        log.info("Received message for processing kweet with id: {}", message.getKweet().getId());
        for (int i = 0; i < message.getUserRelations().size(); i++) {
            if (redisTemplate.hasKey(message.getUserRelations().get(i).getFollowerId())) {
                redisTemplate.opsForList().leftPush(message.getUserRelations().get(i).getFollowerId(), objectMapper.writeValueAsString(message.getKweet()));
                log.info("Kweet with id {} pushed to redis cache for username {}", message.getKweet().getId(), message.getKweet().getUsername());
            }
        }
    }
}
