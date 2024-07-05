package enabler.util;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestConfig {

  private String targetName; //web, mobile, apiOnly
  private String platformName; //android, iOS
  private String automationName; // uiautomator2, flutter
  private String appiumUrl;
  private Android android;
  private Ios ios;
  private Api api;
  private Web web;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  public static class Api {

    private String baseUri;
    private String grantType;
    private Object clientId;
    private Object clientSecret;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  public static class Ios {

    private String app;
    private String udid;
    private String deviceName;
    private String browser;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  public static class Android {

    private String app;
    private String appPackage;
    private String appActivity;
    private String browser;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  public static class Web {

    private String browserName;
    private String url;
    private String gridUrl;
  }
}