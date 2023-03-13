package com.resdii.crm.user.controller;

import com.resdii.crm.user.dto.LoginRequestDTO;
import com.resdii.crm.user.service.AuthService;
import com.resdii.ms.common.rest.BaseRestController;
import com.resdii.ms.common.utils.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseRestController {

    private AuthService userService;

    /**
     * Dang nhap
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequest) {
        return RestUtils.responseOk(userService.login(loginRequest));
    }

    @GetMapping("/test")
    public ResponseEntity getTestList() {
        return RestUtils.responseOk(userService.getTestList());
    }

    @RequestMapping("/getUsers")
    public ResponseEntity<Object> getAllUsers() {
        return userService.getAllUsers();
    }
    @Autowired
    public void setUserService(AuthService userService) {
        this.userService = userService;
    }
}
