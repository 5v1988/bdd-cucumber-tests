package enabler.util.config;

import enabler.exception.TestRuntimeException;

public enum TargetName {
  WEB("web"),
  MOBILE("mobile"),
  API_ONLY("apiOnly");

  private final String targetName;
  TargetName(String type) {
    this.targetName = type;
  }

  public String toString() {
    return targetName;
  }

  public static TargetName fromString(String testTarget) {
    for (TargetName p : TargetName.values()) {
      if (p.targetName.equalsIgnoreCase(testTarget)) {
        return p;
      }
    }
    throw new TestRuntimeException("Not a valid test target name");
  }
}
