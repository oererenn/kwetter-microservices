package com.kwetter.userservice.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserModel {

    @Id
    private String id;
    private String username;
    private String email;
    private String birthdate;
    private String location;
    private String registrationDate;
    private Long following;
    private Long followers;
}
