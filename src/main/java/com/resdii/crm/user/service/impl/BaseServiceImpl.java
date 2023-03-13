package com.resdii.crm.user.service.impl;

import com.resdii.crm.user.mapper.TestMapper;
import com.resdii.crm.user.mapper.UserInfoMapper;
import com.resdii.crm.user.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl {

    /**
     * REPOSITORY
     */
    protected TestRepository testRepository;

    protected UserInfoMapper userInfoMapper;

    /**
     * MAPPER
     */
    protected TestMapper testMapper;

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Autowired
    public void setTestMapper(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = this.userInfoMapper;
    }
}
