package com.resdii.crm.user.service.impl;

import com.resdii.crm.user.config.JwtTokenUtil;
import com.resdii.crm.user.domain.Role;
import com.resdii.crm.user.domain.Test;
import com.resdii.crm.user.domain.User;
import com.resdii.crm.user.dto.*;
import com.resdii.crm.user.repository.UserRepository;
import com.resdii.crm.user.service.AuthService;
import com.resdii.ms.common.exception.InvalidRequestException;
import com.resdii.ms.common.utils.JsonUtils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl extends BaseServiceImpl implements AuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    private UserRepository userRepository;

    private JwtTokenUtil jwtTokenUtil;

    private PasswordEncoder bcryptEncoder;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        String passwordMd5 = DigestUtils.md5Hex(loginRequest.getPassword()).toLowerCase();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + loginRequest.getUsername());
        } else {
            if (bcryptEncoder.matches(passwordMd5, user.getPassword())) {
                Map<String, Object> claims = new HashMap<>();

                String token = jwtTokenUtil.generateToken(userRepository.findByUsername(loginRequest.getUsername()));
                LoginResponseDTO loginResponseDTO =new LoginResponseDTO(token);
                return loginResponseDTO;
            } else {
                throw new UsernameNotFoundException("Incorrect password:");

            }
        }
    }

    @Override
    public List<TestDTO> getTestList() {
        return null;
    }
    public ResponseEntity<Object> getAllUsers() {
        List<User> dbUsers = userRepository.findAll();
        if (dbUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // TODO Auto-generated method stub
            List<TestDTO> b =testMapper.toDest(dbUsers);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
    @Override
    public UserInfoDTO getUserInfo(@RequestHeader(name = "Authorization") String token) throws UnsupportedEncodingException {
        String jwtToken = token.split("\\.")[1];
        String a = new String(Base64.decodeBase64(jwtToken), "UTF-8");
        DecodeJWT details = JsonUtils.parseObject(a, DecodeJWT.class);

        User user =userRepository.findUserById(details.getSub());
        Collection<Role> b=user.getRoles();
        return userInfoMapper.toDest(user);

    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setJwtTokenUtil(JwtTokenUtil jwtTokenUtil){
        this.jwtTokenUtil=jwtTokenUtil;
    }
    @Autowired
    public void setBcryptEncoder(PasswordEncoder bcryptEncoder){
        this.bcryptEncoder=bcryptEncoder;
    }

}
