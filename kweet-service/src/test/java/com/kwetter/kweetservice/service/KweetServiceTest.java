package com.kwetter.kweetservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.kweetservice.config.Producer;
import com.kwetter.kweetservice.entity.Kweet;
import com.kwetter.kweetservice.repository.KweetRepository;
import com.kwetter.kweetservice.request.KweetCreationRequest;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KweetServiceTest {

    @Test
    void deleteKweet() {
        final var kweetRepository = mock(KweetRepository.class);
        final var producer = mock(Producer.class);
        final var objectMapper = mock(ObjectMapper.class);
        final var kweetService = new KweetService(kweetRepository, producer,objectMapper);
        final var kweet = mock(Kweet.class);
        kweetService.deleteKweet(kweet.getId());
        verify(kweetRepository, times(1)).deleteById(kweet.getId());
    }

    @Test
    void createKweet() {
        final var kweetRepository = mock(KweetRepository.class);
        final var producer = mock(Producer.class);
        final var objectMapper = mock(ObjectMapper.class);
        final var kweetService = new KweetService(kweetRepository, producer,objectMapper);
        final var request = new KweetCreationRequest("test", "test","test");
        final var kweet = kweetService.createKweet(request);
        verify(kweetRepository, times(1)).save(kweet);
        assertEquals("test", kweet.getContent());
        assertEquals("test", kweet.getUserId());
    }

    @Test
    void getKweet() {
        final var kweetRepository = mock(KweetRepository.class);
        final var producer = mock(Producer.class);
        final var objectMapper = mock(ObjectMapper.class);
        final var kweetService = new KweetService(kweetRepository, producer,objectMapper);
        final var kweet = Kweet.builder().id("1234").content("test").build();
        kweetService.getKweet(kweet.getId());
        verify(kweetRepository, times(1)).findById(kweet.getId());
        assertEquals("1234", kweet.getId());
    }

    @Test
    void checkKweetLengthSuccess() {
        final var kweetService = mock(KweetService.class);
        final var kweet = Kweet.builder().id(UUID.randomUUID().toString()).content("test").build();
        doNothing().when(kweetService).checkKweetLength(kweet.getContent());
        assertThat(kweet.getContent().length(),lessThan(140));
        kweetService.checkKweetLength(kweet.getContent());
        assertDoesNotThrow(() -> kweetService.checkKweetLength(kweet.getContent()));
    }

    @Test
    void checkKweetLengthFail() {
        final var kweetRepository = mock(KweetRepository.class);
        final var producer = mock(Producer.class);
        final var objectMapper = mock(ObjectMapper.class);
        final var kweetService = new KweetService(kweetRepository, producer,objectMapper);

        final var charArray = new char[150];
        Arrays.fill(charArray, 'a');
        final var content = new String(charArray);

        assertEquals(150, content.length());
        assertThat(content.length(),greaterThan(140));
        assertThrows(IllegalArgumentException.class, () -> kweetService.checkKweetLength(content));
    }
}