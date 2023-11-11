package com.wu.service;

import com.wu.entity.User;

public interface UserService {
    Integer checkLogin(String number, String password);

    User getUserByNumber(String number);
}
