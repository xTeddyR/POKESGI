package com.pokesgi.user;

/**
 * Created by ostro on 25/03/2017.
 */
public interface UserService {

    void createUser(UserEntity userEntity);

     UserEntity canConnect(String login, String password);
}
