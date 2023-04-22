package jpabook.jpashop.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommonResponse {
    boolean isSuccess;
    int code;
    String message;

    public CommonResponse(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
