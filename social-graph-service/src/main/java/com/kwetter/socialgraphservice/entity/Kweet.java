package com.kwetter.socialgraphservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
