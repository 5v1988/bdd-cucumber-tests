package model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateBeneficiaryErrorResponse {
    public List<Error> errors;
    public String msg;
    public String code;
    public Object data;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Error{
        public String field;
        public String message;
    }

}
