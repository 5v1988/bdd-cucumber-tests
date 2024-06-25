package enabler.util.config;

import enabler.exception.TestRuntimeException;

public enum BrowserName {
  CHROME("chrome"),
  FIREFOX("firefox"),
  EDGE("edge");
  private final String browserName;
  BrowserName(String type) {
    this.browserName = type;
  }

  public String toString() {
    return browserName;
  }

  public static BrowserName fromString(String platform) {
    for (BrowserName p : BrowserName.values()) {
      if (p.browserName.equalsIgnoreCase(platform)) {
        return p;
      }
    }
    throw new TestRuntimeException("Not a valid browser selection");
  }
}
