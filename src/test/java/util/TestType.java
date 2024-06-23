package util;

public enum TestType {
  MOBILE_ONLY("mobileOnly"),
  API_ONLY("apiOnly"),
  WEB_ONLY("webOnly"),
  ANY("any");

  private final String testType;
  TestType(String type){
    this.testType = type;
  }

  public String toString() {
    return testType;
  }
}
