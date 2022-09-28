package com.kwetter.kweetprocessingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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
