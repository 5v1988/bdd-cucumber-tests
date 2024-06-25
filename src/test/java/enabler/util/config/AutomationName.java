package enabler.util.config;

import enabler.exception.TestRuntimeException;

public enum AutomationName {
  CHROME("chrome"),
  FIREFOX("firefox"),
  EDGE("edge"),
  UIAUTOMATOR2("uiautomator2"),
  XCUITEST("xcuitest"),
  FLUTTER("flutter");

  private final String automationName;

  AutomationName(String type) {
    this.automationName = type;
  }

  public String toString() {
    return automationName;
  }

  public static AutomationName fromString(String automationName) {
    for (AutomationName a : AutomationName.values()) {
      if (a.automationName.equalsIgnoreCase(automationName)) {
        return a;
      }
    }
    throw new TestRuntimeException("Not a valid automation name");
  }
}
