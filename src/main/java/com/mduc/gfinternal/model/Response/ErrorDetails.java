package com.mduc.gfinternal.model.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
    private String message;
    private String code;

    public ErrorDetails(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
