package com.pravin.raha.lexorank4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class LexoDecimalTest {
  @Test
  public void shouldCompareToEquals() {
    var dec1 = LexoDecimal.from(LexoInteger.parse("12", LexoRank.NUMERAL_SYSTEM));
    var dec2 = LexoDecimal.from(LexoInteger.parse("12", LexoRank.NUMERAL_SYSTEM));
    assertEquals(dec1.compareTo(dec2), 0);
  }

  @Test
  public void shouldCompareToGreater() {
    var dec1 = LexoDecimal.from(LexoInteger.parse("0", LexoRank.NUMERAL_SYSTEM));
    var dec2 = LexoDecimal.from(LexoInteger.parse("1", LexoRank.NUMERAL_SYSTEM));
    assertEquals(dec2.compareTo(dec1), 1);
  }

  @Test
  public void shouldCompareToLess() {
    var dec1 = LexoDecimal.from(LexoInteger.parse("0", LexoRank.NUMERAL_SYSTEM));
    var dec2 = LexoDecimal.from(LexoInteger.parse("1", LexoRank.NUMERAL_SYSTEM));
    assertEquals(dec1.compareTo(dec2), -1);
  }

  @Test
  public void shouldEqualsFormatFromParse() {
    var dec1 = LexoDecimal.from(LexoInteger.parse("12", LexoRank.NUMERAL_SYSTEM));
    assertEquals(dec1.format(), "12");
  }

  @Test
  public void shouldEqualsFromString() {
    var dec1 = LexoDecimal.from(LexoInteger.parse("12", LexoRank.NUMERAL_SYSTEM));
    var dec2 = LexoDecimal.from(LexoInteger.parse("12", LexoRank.NUMERAL_SYSTEM));
    assertEquals(dec1, dec2);
  }

  @Test
  public void shouldNonEqualsFromString() {
    var dec1 = LexoDecimal.from(LexoInteger.parse("12", LexoRank.NUMERAL_SYSTEM));
    var dec2 = LexoDecimal.from(LexoInteger.parse("120", LexoRank.NUMERAL_SYSTEM));
    assertNotEquals(dec1, dec2);
  }
}
