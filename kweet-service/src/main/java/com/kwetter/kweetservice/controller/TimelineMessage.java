package com.kwetter.kweetservice.controller;

import com.kwetter.kweetservice.entity.Kweet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimelineMessage {
    private String followerId;
    private List<Kweet> kweets;
}
