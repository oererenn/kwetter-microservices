package com.example.timelineservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Kweet {
    @Id
    private String id;
    private String userId;
    private String username;
    private String content;
    private Long likeCount;
    private Long retweetCount;
    private Long replyCount;
}

