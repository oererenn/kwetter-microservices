package com.kwetter.kweetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class KweetServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KweetServiceApplication.class, args);
    }

}
