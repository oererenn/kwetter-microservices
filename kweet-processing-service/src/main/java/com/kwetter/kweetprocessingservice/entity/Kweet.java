package com.kwetter.kweetprocessingservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

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