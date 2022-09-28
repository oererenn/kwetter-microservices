package com.example.timelineservice.controller;

import com.example.timelineservice.entity.Kweet;
import com.example.timelineservice.service.TimelineService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/timelines")
public class TimelineController {
    private TimelineService timelineService;

    @GetMapping(value = "/{userId}")
    public List<Kweet> getHomeTimeline(@PathVariable String userId){
        return timelineService.getHomeTimelineByUserId(userId);
    }
}
