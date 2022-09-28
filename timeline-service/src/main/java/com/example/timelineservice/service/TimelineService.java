package com.example.timelineservice.service;

import com.example.timelineservice.entity.HomeTimeline;
import com.example.timelineservice.entity.Kweet;
import com.example.timelineservice.entity.TimelineMessage;
import com.example.timelineservice.repository.HomeTimelineRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TimelineService {
    private final HomeTimelineRepository homeTimelineRepository;
    private final RedisTemplate redisTemplate;

    private final ObjectMapper objectMapper;

    public void saveHomeTimeline(TimelineMessage message) {
        if (!existsByUserId(message.getFollowerId())) {
            final var homeTimeline =
                    HomeTimeline.builder()
                            .userId(message.getFollowerId())
                            .kweets(message.getKweets())
                            .build();
            homeTimelineRepository.save(homeTimeline);
            saveHomeTimelineToRedis(message.getFollowerId());
            log.info("Home timeline created for user: {}", message.getFollowerId());
        } else {
            HomeTimeline homeTimeline = homeTimelineRepository.findByUserId(message.getFollowerId());
            homeTimeline.getKweets().addAll(message.getKweets());
            homeTimelineRepository.save(homeTimeline);
            saveHomeTimelineToRedis(message.getFollowerId());
            log.info("New kweets added for user: {}", message.getFollowerId());
        }
    }

    public void removeKweetsFromHomeTimeline(TimelineMessage message) {
        if (existsByUserId(message.getFollowerId())) {
            HomeTimeline homeTimeline = homeTimelineRepository.findByUserId(message.getFollowerId());
            homeTimeline.getKweets().removeAll(message.getKweets());
            homeTimelineRepository.save(homeTimeline);
            resetHomeTimeline(message.getFollowerId());
            log.info("Kweets removed from home timeline of user: {}", message.getFollowerId());
        }
    }

    //check if timeline exists
    public boolean existsByUserId(String userId) {
        return homeTimelineRepository.existsByUserId(userId);
    }

    //get home timeline by userId from cache if not get from database
    public List<Kweet> getHomeTimelineByUserId(String userId) {
        List<Kweet> kweets = new ArrayList<>();

        if (redisTemplate.hasKey(userId)) {
            kweets = convertTimeline(userId);
            var test = homeTimelineRepository.findByUserId(userId);
            var kweetList = test.getKweets();
            if(kweetList.size() != kweets.size()){
                kweetList = kweets.stream().distinct().collect(Collectors.toList());
                test.setKweets(kweetList);
                homeTimelineRepository.save(test);
            }
        } else {
            if (existsByUserId(userId)) {
                kweets = homeTimelineRepository.findByUserId(userId).getKweets();
                if(kweets.size() > 0) {
                    redisTemplate.opsForList().leftPushAll(userId, kweets);
                }

            }
        }
        return kweets;
    }

    //save home timeline to redis cache as list
    public void saveHomeTimelineToRedis(String userId) {
        HomeTimeline homeTimeline = homeTimelineRepository.findByUserId(userId);
        if(homeTimeline.getKweets().size() > 0) {
            redisTemplate.delete(userId);
            redisTemplate.opsForList().leftPushAll(userId, homeTimeline.getKweets());
            log.info("Home timeline saved to redis cache for user: {}", userId);
        }
    }

    public List<Kweet> convertTimeline(String userId){
        List<Object> kweets = redisTemplate.opsForList().range(userId, 0, -1);
        List<Kweet> kweetList = new ArrayList<>();
        for (int i = 0; i < kweets.size(); i++) {
            if (kweets.get(i) instanceof String) {
                try {
                    kweetList.add(objectMapper.readValue((String) kweets.get(i), Kweet.class));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }
            kweetList.add((Kweet) kweets.get(i));
        }
        return kweetList;
    }

    public void resetHomeTimeline(String userId) {
        redisTemplate.delete(userId);
        saveHomeTimelineToRedis(userId);
    }

}
