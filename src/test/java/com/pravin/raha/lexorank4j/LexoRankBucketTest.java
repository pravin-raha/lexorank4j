package com.pravin.raha.lexorank4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LexoRankBucketTest {

  @Test
  public void shouldEqualsFromString() {
    LexoRankBucket bucket0 = LexoRankBucket.resolve(1);
    LexoRankBucket bucket1 = LexoRankBucket.resolve(1);
    assertTrue(bucket0.equals(bucket1));
  }

  @Test
  public void shouldNonEqualsFromString() {
    LexoRankBucket bucket0 = LexoRankBucket.resolve(0);
    LexoRankBucket bucket1 = LexoRankBucket.resolve(1);
    assertFalse(bucket0.equals(bucket1));
  }

  @Test
  public void shouldResolveBuckets() {
    LexoRankBucket bucket0 = LexoRankBucket.resolve(0);
    LexoRankBucket bucket1 = LexoRankBucket.resolve(1);
    LexoRankBucket bucket2 = LexoRankBucket.resolve(2);

    assertEquals(bucket0.format(), "0");
    assertEquals(bucket1.format(), "1");
    assertEquals(bucket2.format(), "2");
    assertThrows(IllegalArgumentException.class, () -> LexoRankBucket.resolve(-1));
    assertThrows(IllegalArgumentException.class, () -> LexoRankBucket.resolve(3));
  }
}
