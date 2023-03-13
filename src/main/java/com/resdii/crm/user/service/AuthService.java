package com.resdii.crm.user.service;

import com.resdii.crm.user.dto.LoginRequestDTO;
import com.resdii.crm.user.dto.LoginResponseDTO;
import com.resdii.crm.user.dto.TestDTO;
import com.resdii.crm.user.dto.UserInfoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthService {
    /**
     * Dang nhap he thong
     * @return
     */
    LoginResponseDTO login(LoginRequestDTO loginRequest);

    /**
     * Demo truy van du lieu tu Database
     * @return
     */
    List<TestDTO> getTestList();

    ResponseEntity<Object> getAllUsers();
}
