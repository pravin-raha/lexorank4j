package com.pravin.raha.lexorank4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class LexoIntegerTest {

  @Test
  public void shouldCompareToEquals() {
    LexoInteger int1 = LexoInteger.parse("12", LexoRank.NUMERAL_SYSTEM);
    LexoInteger int2 = LexoInteger.parse("12", LexoRank.NUMERAL_SYSTEM);
    assertEquals(int1.compareTo(int2), 0);
  }

  @Test
  public void shouldCompareToGreater() {
    LexoInteger int1 = LexoInteger.parse("0", LexoRank.NUMERAL_SYSTEM);
    LexoInteger int2 = LexoInteger.parse("1", LexoRank.NUMERAL_SYSTEM);
    assertEquals(int2.compareTo(int1), 1);
  }

  @Test
  public void shouldCompareToLess() {
    LexoInteger int1 = LexoInteger.parse("0", LexoRank.NUMERAL_SYSTEM);
    LexoInteger int2 = LexoInteger.parse("1", LexoRank.NUMERAL_SYSTEM);
    assertEquals(int1.compareTo(int2), -1);
  }

  @Test
  public void shouldEqualsFormatFromParse() {
    var int1 = LexoInteger.parse("12", LexoRank.NUMERAL_SYSTEM);
    assertEquals(int1.format(), "12");
  }

  @Test
  public void shouldEqualsFromString() {
    LexoInteger int1 = LexoInteger.parse("12", LexoRank.NUMERAL_SYSTEM);
    LexoInteger int2 = LexoInteger.parse("12", LexoRank.NUMERAL_SYSTEM);
    assertEquals(int1, int2);
  }

  @Test
  public void shouldNonEqualsFromString() {
    LexoInteger int1 = LexoInteger.parse("12", LexoRank.NUMERAL_SYSTEM);
    LexoInteger int2 = LexoInteger.parse("120", LexoRank.NUMERAL_SYSTEM);
    assertNotEquals(int1, int2);
  }
}
