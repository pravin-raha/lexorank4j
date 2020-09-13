package com.pravin.raha.lexorank4j;

import com.pravin.raha.lexorank4j.numerical.system.LexoNumeralSystem;
import java.util.Objects;

public class LexoDecimal implements Comparable<LexoDecimal> {

  private final LexoInteger mag;
  private final int sig;

  private LexoDecimal(LexoInteger mag, int sig) {
    this.mag = mag;
    this.sig = sig;
  }

  public static LexoDecimal half(LexoNumeralSystem sys) {
    var mid = sys.getBase() / 2;
    return make(LexoInteger.make(sys, 1, new int[] {mid}), 1);
  }

  public static LexoDecimal parse(String str, LexoNumeralSystem system) {
    var partialIndex = str.indexOf(system.getRadixPointChar());
    if (str.lastIndexOf(system.getRadixPointChar()) != partialIndex)
      throw new IllegalArgumentException("More than one " + system.getRadixPointChar());

    if (partialIndex < 0) return make(LexoInteger.parse(str, system), 0);

    var intStr = str.substring(0, partialIndex) + str.substring(partialIndex + 1);
    return make(LexoInteger.parse(intStr, system), str.length() - 1 - partialIndex);
  }

  public static LexoDecimal from(LexoInteger integer) {
    return make(integer, 0);
  }

  public static LexoDecimal make(LexoInteger integer, int sig) {
    if (integer.isZero()) return new LexoDecimal(integer, 0);

    var zeroCount = 0;

    for (var i = 0; i < sig && integer.getMag(i) == 0; ++i) ++zeroCount;

    var newInteger = integer.shiftRight(zeroCount);
    var newSig = sig - zeroCount;
    return new LexoDecimal(newInteger, newSig);
  }

  public LexoNumeralSystem getSystem() {
    return mag.getSystem();
  }

  public LexoDecimal add(LexoDecimal other) {
    var tMag = mag;
    var tSig = sig;
    var oMag = other.mag;

    int oSig;
    for (oSig = other.sig; tSig < oSig; ++tSig) tMag = tMag.shiftLeft();

    while (tSig > oSig) {
      oMag = oMag.shiftLeft();
      ++oSig;
    }

    return make(tMag.add(oMag), tSig);
  }

  public LexoDecimal subtract(LexoDecimal other) {
    var thisMag = mag;
    var thisSig = sig;
    var otherMag = other.mag;

    int otherSig;
    for (otherSig = other.sig; thisSig < otherSig; ++thisSig) thisMag = thisMag.shiftLeft();

    while (thisSig > otherSig) {
      otherMag = otherMag.shiftLeft();
      ++otherSig;
    }

    return make(thisMag.subtract(otherMag), thisSig);
  }

  public LexoDecimal multiply(LexoDecimal other) {
    return make(mag.multiply(other.mag), sig + other.sig);
  }

  public LexoInteger floor() {
    return mag.shiftRight(sig);
  }

  public LexoInteger ceil() {
    if (isExact()) return mag;

    var floor = floor();
    return floor.add(LexoInteger.one(floor.getSystem()));
  }

  public boolean isExact() {
    if (sig == 0) return true;

    for (var i = 0; i < sig; ++i) if (mag.getMag(i) != 0) return false;

    return true;
  }

  public int getScale() {
    return sig;
  }

  public LexoDecimal setScale(int nSig) {
    return setScale(nSig, false);
  }

  public LexoDecimal setScale(int nSig, boolean ceiling) {
    if (nSig >= sig) return this;

    if (nSig < 0) nSig = 0;

    var diff = sig - nSig;
    var nmag = mag.shiftRight(diff);
    if (ceiling) nmag = nmag.add(LexoInteger.one(nmag.getSystem()));

    return make(nmag, nSig);
  }

  public String format() {
    var intStr = mag.format();
    if (sig == 0) return intStr;

    StringBuilder sb = new StringBuilder(intStr);
    char head = sb.charAt(0);
    var specialHead =
        head == mag.getSystem().getPositiveChar() || head == mag.getSystem().getNegativeChar();
    if (specialHead) sb.delete(0, 1);

    while (sb.length() < sig + 1) sb.insert(0, mag.getSystem().toChar(0));

    sb.insert(sb.length() - sig, mag.getSystem().getRadixPointChar());
    if (sb.length() - sig == 0) sb.insert(0, mag.getSystem().toChar(0));

    if (specialHead) sb.insert(0, head);

    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LexoDecimal that = (LexoDecimal) o;
    return sig == that.sig && Objects.equals(mag, that.mag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mag, sig);
  }

  @Override
  public String toString() {
    return format();
  }

  @Override
  public int compareTo(LexoDecimal lexoDecimal) {
    if (Objects.equals(this, lexoDecimal)) return 0;
    if (Objects.equals(null, lexoDecimal)) return 1;

    var tMag = mag;
    var oMag = lexoDecimal.mag;
    if (sig > lexoDecimal.sig) oMag = oMag.shiftLeft(sig - lexoDecimal.sig);
    else if (sig < lexoDecimal.sig) tMag = tMag.shiftLeft(lexoDecimal.sig - sig);

    return tMag.compareTo(oMag);
  }
}
