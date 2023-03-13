package com.resdii.crm.user.dto;

import com.resdii.crm.user.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class UserInfoDTO {
    private String id;
    private String username;
    private Collection<Role> roles;
}
