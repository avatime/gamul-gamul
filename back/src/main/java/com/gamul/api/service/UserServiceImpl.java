package com.gamul.api.service;

import com.gamul.api.request.UserRegisterPostReq;
import com.gamul.common.util.JwtTokenUtil;
import com.gamul.common.util.Token;
import com.gamul.db.entity.User;
import com.gamul.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(@Lazy BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserRegisterPostReq userRegisterPostReq) throws Exception {
        User user = new User();
        user.setUsername(userRegisterPostReq.getUserName());
//        user.setPassword(userRegisterPostReq.getPassword());
        user.setPassword(passwordEncoder.encode(userRegisterPostReq.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public boolean findUsername(String username) throws Exception {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user){
        userRepository.delete(user);
    }

    @Override
    public void logout(String username){
        User user = getUserByUsername(username);
        user.setRefreshToken(null);
        userRepository.save(user);
    }

    @Override
    public String refreshToken(String refreshToken) throws Exception {
        Token token = Token.builder().refreshToken(refreshToken).build();

        return JwtTokenUtil.validateRefreshToken(token);
    }
}
