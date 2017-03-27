package com.pokesgi.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ostro on 26/03/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByLoginAndPassword(String login, String password);
}
