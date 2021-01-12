package com.ys.service.impl;

import com.ys.model.dao.UserMapper;
import com.ys.model.pojo.User;
import com.ys.model.pojo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public UserVo findByUsername(UserVo userVo){
        return userMapper.findByUsername(userVo.getuName());
    }
    public UserVo findUserById(UserVo userVo) {
        return userMapper.findUserById(userVo.getuId());
    }
}
