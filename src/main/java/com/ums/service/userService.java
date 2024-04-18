package com.ums.service;

import com.ums.payload.UserDTO;
import com.ums.payload.loginDTO;


public interface userService {
    void addUser(UserDTO user);
    String VerifyLogin(loginDTO logindto);
}
