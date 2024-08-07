package com.mduc.gfinternal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class AuthenticationResponse {
    @Getter
    @JsonProperty("access_token")
    private String accessToken;
    @Getter
    @JsonProperty("refresh_token")
    private String refreshToken;
    @Getter
    @JsonProperty
    private String message;
    @Getter
    @JsonProperty
    private String role;
    @Getter
    @JsonProperty
    private String fullName;
    @Getter
    @JsonProperty
    private String employeeId;
    @Getter
    @JsonProperty
    private String user;
    public AuthenticationResponse(String accessToken, String refreshToken, String message,String role, String fullName, String user) {
        this.accessToken = accessToken;
        this.message = message;
        this.refreshToken = refreshToken;
        this.role = role;
        this.fullName = fullName;
        this.user = user;
    }
}
