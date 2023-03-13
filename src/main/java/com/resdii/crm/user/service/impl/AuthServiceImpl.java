package com.resdii.crm.user.service.impl;

import com.resdii.crm.user.config.JwtTokenUtil;
import com.resdii.crm.user.domain.Test;
import com.resdii.crm.user.domain.User;
import com.resdii.crm.user.dto.LoginRequestDTO;
import com.resdii.crm.user.dto.LoginResponseDTO;
import com.resdii.crm.user.dto.TestDTO;
import com.resdii.crm.user.dto.UserInfoDTO;
import com.resdii.crm.user.repository.UserRepository;
import com.resdii.crm.user.service.AuthService;
import com.resdii.ms.common.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl extends BaseServiceImpl implements AuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        String passwordmd5 = DigestUtils.md5Hex(loginRequest.getPassword()).toLowerCase();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + loginRequest.getUsername());
        } else {
            if (bcryptEncoder.matches(passwordmd5, user.getPassword())) {
                Map<String, Object> claims = new HashMap<>();

                String token = jwtTokenUtil.generateToken(userRepository.findByUsername(loginRequest.getUsername()));
                LoginResponseDTO loginResponseDTO =new LoginResponseDTO(token);
                return loginResponseDTO;
            } else {
                throw new UsernameNotFoundException("Mkhau không chính xác " + user.getPassword());
            }
        }
    }

    @Override
    public List<TestDTO> getTestList() {
        return null;
    }

    //    @Override
//    public List<TestDTO> getTestList() {
//        List<Test> testList = testRepository.findAll();
//        return testMapper.toDest(testList);
//    }
    public ResponseEntity<Object> getAllUsers() {
        List<User> dbUsers = userRepository.findAll();
        if (dbUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // TODO Auto-generated method stub
            List<TestDTO> b =testMapper.toDest(dbUsers);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }



    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
