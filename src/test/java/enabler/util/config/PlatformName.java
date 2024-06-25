package enabler.util.config;

import enabler.exception.TestRuntimeException;

public enum PlatformName {
  ANDROID("android"),
  IOS("iOS");

  private final String platformName;
  PlatformName(String type) {
    this.platformName = type;
  }

  public String toString() {
    return platformName;
  }

  public static PlatformName fromString(String platform) {
    for (PlatformName p : PlatformName.values()) {
      if (p.platformName.equalsIgnoreCase(platform)) {
        return p;
      }
    }
    throw new TestRuntimeException("Not a valid platformName name");
  }
}
