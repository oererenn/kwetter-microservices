package com.kwetter.kweetservice.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
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
