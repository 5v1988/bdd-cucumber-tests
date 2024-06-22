package model.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateBeneficiaryResponse {
    public String msg;
    public String code;
    public Data data;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Data{
        public String id;
        public String name;
        public String email;
        public String type;
        public String relationship;
        public Object address;
        public Object city;
        public Object state;
        public Object country;
        public Object zipCode;
    }
}
