package com.kwetter.kweetservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/auth")
public class testController {
    @GetMapping(value = "/private")
    @PreAuthorize("hasAuthority('read:kweets')")
    public ResponseEntity<String> privateUsers(JwtAuthenticationToken authentication) {
            return ResponseEntity.ok("user content");
    }
    @GetMapping(value = "/private-scoped")
    @PreAuthorize("hasAuthority('read:admin')")
    public String privateAdmins() {
        return "admin content1";
    }
}
