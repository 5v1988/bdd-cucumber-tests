package enabler.util;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestConfig {
  private String platName; //web, android, iOS, api
  private String automationName; //chrome, edge, uiautomator2, flutter, firefox
  private String url;
  private String gridUrl;
  private String apiBaseUri;
  private String grantType;
  private String clientId;
  private String clientSecret;
}