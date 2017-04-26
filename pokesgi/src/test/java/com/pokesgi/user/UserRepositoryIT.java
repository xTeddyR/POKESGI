package com.pokesgi.user;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

/**
 * Created by ostro on 27/03/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryIT {

    @Autowired
    UserRepository userRepository;

    Integer firstTestUserId;

    @Before
    public void initialize_data(){
        UserEntity userEntity = UserEntity.builder()
                .login("loginTest")
                .password("passwordTest")
                .username("Test")
                .firstname("")
                .lastname("")
                .creationDate(Calendar.getInstance().getTime())
                .build();

        userRepository.save(userEntity);

        firstTestUserId = userEntity.getIdUser();
    }

    @After
    public void delete_data(){
        userRepository.delete(firstTestUserId);
    }

    @Test
    public void should_match_login_and_password(){
        String login = "loginTest";
        String password = "passwordTest";

        UserEntity userEntity = userRepository.findByLoginAndPassword(login, password);

        Assert.assertNotNull(userEntity);
        Assert.assertEquals(firstTestUserId, userEntity.getIdUser());
        Assert.assertEquals(login, userEntity.getLogin());
        Assert.assertEquals(password, userEntity.getPassword());
    }

    @Test
    public void should_unmatch_login_and_password(){
        String login = "password";
        String password = "login";

        UserEntity userEntity = userRepository.findByLoginAndPassword(login, password);

        Assert.assertNull(userEntity);
    }

    @Test
    public void should_find_all_users(){
        List<UserEntity> allUsers = userRepository.findAll();

        Assert.assertNotNull(allUsers);
        Assert.assertTrue(allUsers.size() > 0);
    }
}
