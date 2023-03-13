package com.resdii.crm.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginResponseDTO {
    private static final long serialVersionUID = -8091879091924046844L;
    private String token;


    public LoginResponseDTO() {

    }

    public LoginResponseDTO(String token) {
        this.token = token;
    }

//    public LoginResponseDTO() {
//
//    }

//    public String getToken(String token) {
//        return this.token;
//    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
