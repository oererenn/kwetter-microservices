package com.example.timelineservice.repository;

import com.example.timelineservice.entity.UserTimeline;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserTimelineRepository extends MongoRepository<UserTimeline, String> {
}
