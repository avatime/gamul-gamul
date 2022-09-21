package com.gamul.api.service;

import com.gamul.api.request.UserRegisterPostReq;
import com.gamul.db.entity.User;

public interface UserService {
    User createUser(UserRegisterPostReq userRegisterPostReq) throws Exception;
    boolean findUsername(String username) throws Exception;
    User getUserByUsername(String username);
}
