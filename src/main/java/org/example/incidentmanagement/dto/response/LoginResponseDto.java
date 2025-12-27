package org.example.incidentmanagement.dto.response;

public class LoginResponseDto {



    private String token;
    private String tokenType = "Bearer";

    public LoginResponseDto(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }


}
