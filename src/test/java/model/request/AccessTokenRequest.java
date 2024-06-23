package model.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccessTokenRequest {

  private String grant_type;
  private String client_id;
  private String client_secret;
}
