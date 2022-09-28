package com.kwetter.socialgraphservice.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user-relation")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserRelationModel {
    @Id
    private String id;
    private String followerId;
    private String followingId;
}
