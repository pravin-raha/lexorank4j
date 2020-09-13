package com.github.pravin.raha.lexorank4j.data;

public class LexoRankTestData {

  private int prevStep;
  private int nextStep;
  private String expected;

  public LexoRankTestData(int prevStep, int nextStep, String expected) {
    this.prevStep = prevStep;
    this.nextStep = nextStep;
    this.expected = expected;
  }

  public String getExpected() {
    return expected;
  }

  public void setExpected(String expected) {
    this.expected = expected;
  }

  public int getNextStep() {
    return nextStep;
  }

  public void setNextStep(int nextStep) {
    this.nextStep = nextStep;
  }

  public int getPrevStep() {
    return prevStep;
  }

  public void setPrevStep(int prevStep) {
    this.prevStep = prevStep;
  }
}
