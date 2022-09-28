package com.kwetter.socialgraphservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KweetProcessing {
    private Kweet kweet;
    private List<UserRelationModel> userRelations;
}
