package enabler.util;

import enabler.exception.TestRuntimeException;

public enum PlatformName {
  WEB("web"),
  ANDROID("android"),
  IOS("iOS"),
  API_ONLY("apiOnly");

  private final String platName;
  PlatformName(String type) {
    this.platName = type;
  }

  public String toString() {
    return platName;
  }

  public static PlatformName fromString(String platform) {
    for (PlatformName p : PlatformName.values()) {
      if (p.platName.equalsIgnoreCase(platform)) {
        return p;
      }
    }
    throw new TestRuntimeException("Not a valid platformName name");
  }
}
