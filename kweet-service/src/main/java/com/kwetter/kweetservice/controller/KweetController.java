package com.kwetter.kweetservice.controller;

import com.kwetter.kweetservice.entity.Kweet;
import com.kwetter.kweetservice.request.KweetCreationRequest;
import com.kwetter.kweetservice.service.KweetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/kweets")
public class KweetController {

    private final KweetService kweetService;
    @PostMapping
    public void createKweet(@RequestBody KweetCreationRequest request) {
        kweetService.createKweet(request);
    }

    @GetMapping(value = "/{id}")
    public Kweet getKweet(@PathVariable String id) {
        return kweetService.getKweet(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteKweet(@PathVariable String id) {
        kweetService.deleteKweet(id);
    }

    @PutMapping(value = "/{id}")
    public void likeKweet(@PathVariable String id) {
        kweetService.likeKweet(id);
    }

    @GetMapping(value = "/userId/{userId}")
    public List<Kweet> getKweetsByUserId(@PathVariable String userId) {
        return kweetService.getKweetsByUserId(userId);
    }

    //get kweets by username
    @GetMapping(value = "/username/{username}")
    public List<Kweet> getKweetsByUsername(@PathVariable String username) {
        return kweetService.getKweetsByUsername(username);
    }
}
