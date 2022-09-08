package com.gamul.api.service;

import com.gamul.db.entity.User;

public interface UserService {
    User createUser(User user) throws Exception;
}
