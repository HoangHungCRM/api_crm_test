package com.resdii.crm.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginRequestDTO {
    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;

    public LoginRequestDTO()
    {
    }

    public LoginRequestDTO(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
