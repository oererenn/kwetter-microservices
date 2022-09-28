package com.example.timelineservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user-timeline")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTimeline {
    @Id
    private String userId;
    private List<Kweet> kweets;
}
