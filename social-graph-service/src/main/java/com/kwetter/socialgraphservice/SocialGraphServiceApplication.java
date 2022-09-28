package com.kwetter.socialgraphservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SocialGraphServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SocialGraphServiceApplication.class, args);
    }

}
