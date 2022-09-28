package com.kwetter.kweetservice.entity;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class KweetTest {
    @Test
    void getId() {
        String id = UUID.randomUUID().toString();
        Kweet kweet = new Kweet();
        kweet.setId(id);
        assertEquals(kweet.getId(),id);
    }

    @Test
    void getContent() {
        String content = "test";
        Kweet kweet = new Kweet();
        kweet.setContent(content);
        assertEquals(kweet.getContent(),content);
    }

    @Test
    void getUsername() {
        String username = "oereren";
        Kweet kweet = new Kweet();
        kweet.setId(username);
        assertEquals(kweet.getId(),username);
    }

    @Test
    void getLikeCount() {
        Long likeCount = 1L;
        Kweet kweet = new Kweet();
        kweet.setLikeCount(likeCount);
        assertEquals(kweet.getLikeCount(),likeCount);
    }

    @Test
    void getRetweetCount() {
        Long retweetCount = 1L;
        Kweet kweet = new Kweet();
        kweet.setRetweetCount(retweetCount);
        assertEquals(kweet.getRetweetCount(),retweetCount);
    }

    @Test
    void getReplyCount() {
        Long replyCount = 1L;
        Kweet kweet = new Kweet();
        kweet.setReplyCount(replyCount);
        assertEquals(kweet.getReplyCount(),replyCount);
    }

    @Test
    void builder() {
        String id = UUID.randomUUID().toString();
        String content = "test";
        String username = "oereren";
        Long likeCount = 1L;
        Long retweetCount = 1L;
        Long replyCount = 1L;
        Kweet kweet = Kweet.builder()
                .id(id)
                .content(content)
                .userId(username)
                .likeCount(likeCount)
                .retweetCount(retweetCount)
                .replyCount(replyCount)
                .build();
        assertEquals(kweet.getId(),id);
        assertEquals(kweet.getContent(),content);
        assertEquals(kweet.getUserId(),username);
        assertEquals(kweet.getLikeCount(),likeCount);
        assertEquals(kweet.getRetweetCount(),retweetCount);
        assertEquals(kweet.getReplyCount(),replyCount);
    }

    @Test
    void testConstructor() {
        final var kweet = new Kweet("1","oereren", "test", "oereren", 1L, 1L, 1L);
        assertEquals("1", kweet.getId());
        assertEquals("test", kweet.getUsername());
        assertEquals("oereren", kweet.getContent());
        assertEquals(1L, kweet.getLikeCount());
        assertEquals(1L, kweet.getRetweetCount());
        assertEquals(1L, kweet.getReplyCount());

    }

    @Test
    void testEmptyConstructor(){
        final var kweet = new Kweet();
        assertNull(kweet.getId());
        assertNull(kweet.getContent());
        assertNull(kweet.getUserId());
        assertNull(kweet.getLikeCount());
        assertNull(kweet.getRetweetCount());
        assertNull(kweet.getReplyCount());
    }

    @Test
    void testEquals() {
        final var kweet1 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        final var kweet2 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        assertEquals(kweet1, kweet2);
    }

    @Test
    void testNotEquals() {
        final var kweet1 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        final var kweet2 = new Kweet("2", "test", "test", "oereren", 1L, 1L, 1L);
        assertNotEquals(kweet1, kweet2);
    }

    @Test
    void testEqualsNull() {
        final var kweet1 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        Kweet kweet2 = null;
        assertNotEquals(kweet1, kweet2);
    }

    @Test
    void testNotEqualsContent() {
        final var kweet1 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        final var kweet2 = new Kweet("1", "test2", "test", "oereren", 1L, 1L, 1L);
        assertNotEquals(kweet1, kweet2);
    }

    @Test
    void testNotEqualsUsername() {
        final var kweet1 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        final var kweet2 = new Kweet("1", "test", "test", "oereren2", 1L, 1L, 1L);
        assertNotEquals(kweet1, kweet2);
    }

    @Test
    void testNotEqualsLikeCount() {
        final var kweet1 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        final var kweet2 = new Kweet("1", "test", "test", "oereren", 2L, 1L, 1L);
        assertNotEquals(kweet1, kweet2);
    }

    @Test
    void testNotEqualsRetweetCount() {
        final var kweet1 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        final var kweet2 = new Kweet("1", "test", "test", "oereren", 1L, 2L, 1L);
        assertNotEquals(kweet1, kweet2);
    }

    @Test
    void testNotEqualsReplyCount() {
        final var kweet1 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        final var kweet2 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 2L);
        assertNotEquals(kweet1, kweet2);
    }


    @Test
    void testEqualsWrongClass() {
        final var kweet1 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        final var kweet2 = new Object();
        assertNotEquals(kweet1, kweet2);
    }

    @Test
    void testHashCode() {
        final var kweet1 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        final var kweet2 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        assertEquals(kweet1.hashCode(), kweet2.hashCode());
    }

    @Test
    void testHashCodeNotEquals() {
        final var kweet1 = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        final var kweet2 = new Kweet("2", "test", "test", "oereren", 1L, 1L, 1L);
        assertNotEquals(kweet1.hashCode(), kweet2.hashCode());
    }

    @Test
    void testToString() {
        final var kweet = new Kweet("1", "test", "test", "oereren", 1L, 1L, 1L);
        assertEquals("Kweet(id=1, userId=test, username=test, content=oereren, likeCount=1, retweetCount=1, replyCount=1)", kweet.toString());
    }
}