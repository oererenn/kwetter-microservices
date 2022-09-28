package com.example.commentservice.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import lombok.*;

import java.util.Objects;

@Container(containerName = "comment")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String id;
    private String content;
    private String userId;
    private String kweetId;
    private Long likeCount;
    private Long retweetCount;
    private Long replyCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id) && content.equals(comment.content) && userId.equals(comment.userId) && kweetId.equals(comment.kweetId) && Objects.equals(likeCount, comment.likeCount) && Objects.equals(retweetCount, comment.retweetCount) && Objects.equals(replyCount, comment.replyCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, userId, kweetId, likeCount, retweetCount, replyCount);
    }
}
