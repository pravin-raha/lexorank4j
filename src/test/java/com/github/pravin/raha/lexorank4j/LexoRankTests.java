package com.github.pravin.raha.lexorank4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.pravin.raha.lexorank4j.data.LexoRankTestData;
import com.github.pravin.raha.lexorank4j.numerical.system.LexoNumeralSystem10;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LexoRankTests {

  @Test
  public void shouldBetweenMoveTo() {

    List<LexoRankTestData> lexoRankTestData =
        List.of(
            new LexoRankTestData(0, 1, "0|0i0000:"),
            new LexoRankTestData(1, 0, "0|0i0000:"),
            new LexoRankTestData(3, 5, "0|10000o:"),
            new LexoRankTestData(5, 3, "0|10000o:"),
            new LexoRankTestData(15, 30, "0|10004s:"),
            new LexoRankTestData(31, 32, "0|10006s:"),
            new LexoRankTestData(100, 200, "0|1000x4:"),
            new LexoRankTestData(200, 100, "0|1000x4:"));

    for (LexoRankTestData lexoRankTest : lexoRankTestData) {
      // Arrange
      var prevRank = LexoRank.min();
      for (var i = 0; i < lexoRankTest.getPrevStep(); i++) prevRank = prevRank.genNext();

      var nextRank = LexoRank.min();
      for (var i = 0; i < lexoRankTest.getNextStep(); i++) nextRank = nextRank.genNext();

      // Act
      var between = prevRank.between(nextRank);

      // Assert
      assertEquals(between.toString(), lexoRankTest.getExpected());
    }
  }

  @Test
  public void shouldBetween() {
    var lexorank = LexoRank.min();
    var lexorank1 = lexorank.genNext();
    var between = lexorank.between(lexorank1);
    assertTrue(lexorank.CompareTo(between) < 0);
    assertTrue(lexorank1.CompareTo(between) > 0);
  }

  @Test
  public void shouldBetweenMaxGenPrev() {
    var maxRank = LexoRank.max();
    var prevRank = maxRank.genPrev();
    var between = maxRank.between(prevRank);
    assertEquals(between.toString(), "0|yzzzzz:");
    assertTrue(maxRank.CompareTo(between) > (0));
    assertTrue(prevRank.CompareTo(between) < 0);
  }

  @Test
  public void shouldBetweenMiddle() {
    // Arrange
    var minRank = LexoRank.min();
    var maxRank = LexoRank.max();
    // Act
    var middleRank = LexoRank.middle();
    var prevMiddleRank = middleRank.genPrev();
    var nextMiddleRank = middleRank.genNext();
    // Assert
    assertEquals(middleRank.toString(), "0|hzzzzz:");
    assertTrue(minRank.CompareTo(middleRank) < 0);
    assertTrue(maxRank.CompareTo(middleRank) > 0);
    assertTrue(prevMiddleRank.CompareTo(middleRank) < 0);
    assertTrue(nextMiddleRank.CompareTo(middleRank) > 0);
  }

  @Test
  public void shouldBetweenMinGenNext() {
    var minRank = LexoRank.min();
    var nextRank = minRank.genNext();
    var between = minRank.between(nextRank);
    assertEquals(between.toString(), "0|0i0000:");
    assertTrue(minRank.CompareTo(between) < 0);
    assertTrue(nextRank.CompareTo(between) > 0);
  }

  @Test
  public void shouldBetweenMinMax() {
    var minRank = LexoRank.min();
    var maxRank = LexoRank.max();
    var between = minRank.between(maxRank);
    assertEquals(between.toString(), "0|hzzzzz:");
    assertTrue(minRank.CompareTo(between) < 0);
    assertTrue(maxRank.CompareTo(between) > 0);
  }

  @Test
  public void shouldBetweenTwice() {
    var lexorank = LexoRank.min();
    var lexorank1 = lexorank.genNext();
    var between = lexorank.between(lexorank1);
    var between1 = between.between(lexorank1);
    assertTrue(between1.CompareTo(between) > 0);
    assertTrue(lexorank1.CompareTo(between1) > 0);
  }

  @Test
  public void shouldCorrectOrderByValue() {
    LexoRank cursorLexoRank = LexoRank.min();
    List<LexoRank> items = new java.util.ArrayList<>(Collections.singletonList(cursorLexoRank));
    for (int i = 0; i < 100000; i++) {
      cursorLexoRank = cursorLexoRank.genNext();
      items.add(cursorLexoRank);
    }

    List<String> originItems = items.stream().map(LexoRank::format).collect(Collectors.toList());
    List<String> sortItems = originItems;
    Collections.shuffle(sortItems);

    Collections.sort(sortItems);

    Assertions.assertEquals(originItems, sortItems);
  }

  @Test
  public void shouldCreateMillionInstances() {
    LexoRank cursorLexoRank = LexoRank.min();
    List<LexoRank> items = new java.util.ArrayList<>(Collections.singletonList(cursorLexoRank));
    for (int i = 0; i < 1000000; i++) {
      cursorLexoRank = cursorLexoRank.genNext();
      items.add(cursorLexoRank);
    }

    assertEquals(items.size(), 1000001);
  }

  @Test
  public void shouldDifferentNumeralSystemByFrom() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () ->
            LexoRank.from(LexoRankBucket.min(), LexoDecimal.parse("1", new LexoNumeralSystem10())));
  }

  @Test
  public void shouldEqualsFormatFromParse() {
    LexoRank lexorank = LexoRank.parse("1|12345");
    assertEquals(lexorank.format(), "1|12345");
  }

  @Test
  public void shouldGenNext() {
    LexoRank lexorank = LexoRank.min();
    LexoRank nextLexorank = lexorank.genNext();
    assertEquals(lexorank.CompareTo(nextLexorank), -1);
  }

  @Test
  public void Should_GenPrev() {
    LexoRank lexorank = LexoRank.max();
    LexoRank prevLexorank = lexorank.genPrev();
    assertEquals(lexorank.CompareTo(prevLexorank), 1);
  }

  @Test
  public void shouldInNextBucket() {
    LexoRank lexorank = LexoRank.min();
    LexoRank lexorank1 = lexorank.inNextBucket();
    assertTrue(lexorank.format().contains("0|"));
    assertTrue(lexorank1.format().contains("1|"));
  }

  @Test
  public void shouldInPrevBucket() {
    LexoRank lexorank = LexoRank.min();
    LexoRank lexorank1 = lexorank.inPrevBucket();
    assertTrue(lexorank.format().contains("0|"));
    assertTrue(lexorank1.format().contains("2|"));
  }

  @Test
  public void shouldInstanceByFrom() {
    LexoRank lexorank =
        LexoRank.from(LexoRankBucket.min(), LexoDecimal.parse("1", LexoRank.NUMERAL_SYSTEM));
    assertEquals(lexorank.getBucket().format(), "0");
    assertEquals(lexorank.getDecimal().format(), "1");
  }

  @Test
  public void shouldIsMax() {
    LexoRank lexorank = LexoRank.max();
    assertTrue(lexorank.isMax());
  }

  @Test
  public void shouldIsMin() {
    LexoRank lexorank = LexoRank.min();
    assertTrue(lexorank.isMin());
  }
}
