package com.wu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wu.entity.User;
import com.wu.mapper.UserMapper;
import com.wu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer checkLogin(String number, String password) {
        User user = this.getUserByNumber(number);
        if(ObjectUtils.isEmpty(user)){
            return -1;
        }else {
            if(password.equals(user.getPassword())){
                return 1;
            }else{
                return 0;
            }
        }
    }

    @Override
    public User getUserByNumber(String number) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("number",number);
        User user = this.userMapper.selectOne(wrapper);
        return user;
    }
}
