package model.response;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenResponse {
    private String access_token;
    private String expires_in;
    private String token_type;
    private String scope;
}
