package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestConfig {
  private String url;
  private String browser;
  private String gridUrl;
  private String testType;
  private String apiBaseUri;
  private String grantType;
  private String clientId;
  private String clientSecret;
}