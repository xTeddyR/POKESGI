package com.pokesgi.user;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ostro on 25/03/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(UserEntity userEntity){
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity canConnect(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }
}
