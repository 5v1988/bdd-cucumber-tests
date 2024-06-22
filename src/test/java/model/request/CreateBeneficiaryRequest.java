package model.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateBeneficiaryRequest {
    private String name;
    private String email;
    private String type;
    private String relationship;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
