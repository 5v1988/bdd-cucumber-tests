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
  private String browserName; //chrome, edge, firefox
  private String url;
  private String gridUrl;
  private String appiumUrl;
  private String app;
  private String apiBaseUri;
  private String grantType;
  private String clientId;
  private String clientSecret;
}