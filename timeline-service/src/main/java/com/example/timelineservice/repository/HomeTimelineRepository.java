package com.example.timelineservice.repository;

import com.example.timelineservice.entity.HomeTimeline;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HomeTimelineRepository extends MongoRepository<HomeTimeline, String> {
    boolean existsByUserId(String userId);

    HomeTimeline findByUserId(String followerId);
}
