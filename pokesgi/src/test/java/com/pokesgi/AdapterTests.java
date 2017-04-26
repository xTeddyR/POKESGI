package com.pokesgi;

import com.pokesgi.user.UserDTO;
import com.pokesgi.user.UserEntity;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ostro on 29/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Adapter.class)
public class AdapterTests {

    @Autowired
    Adapter adapter;

    @Test
    public void should_create_userDTO_from_userEntity() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer idUser = 1;
        String login = "login";
        String password = "password";
        String username = "username";
        String firstname = "firstname";
        String lastname = "lastname";
        String mail = "mail";
        Date creationDate = format.parse("2000-01-01 00:00:00");
        Date deleteDate = format.parse("2000-01-01 23:59:59");

        UserDTO userDTO = adapter.toUserDTO(UserEntity.builder()
                                    .idUser(idUser)
                                    .login(login)
                                    .password(password)
                                    .username(username)
                                    .firstname(firstname)
                                    .lastname(lastname)
                                    .mail(mail)
                                    .creationDate(creationDate)
                                    .deleteDate(deleteDate).build());

        Assert.assertNotNull(userDTO);
        Assert.assertEquals(idUser, userDTO.getId());
        Assert.assertEquals(login, userDTO.getLogin());
        Assert.assertEquals(password, userDTO.getPassword());
        Assert.assertEquals(username, userDTO.getUsername());
        Assert.assertEquals(firstname, userDTO.getFirstname());
        Assert.assertEquals(lastname, userDTO.getLastname());
        Assert.assertEquals(mail, userDTO.getMail());
        Assert.assertEquals(creationDate.toString(), userDTO.getCreationDate());
        Assert.assertEquals(deleteDate.toString(), userDTO.getDeleteDate());
    }

    @Test
    public void should_create_userDTO_from_userEntity_2() throws ParseException {
        Integer idUser = -1;
        String login = "";
        String password = "";
        String username = "";
        String firstname = "";
        String lastname = "";
        String mail = "";
        Date creationDate = null;
        Date deleteDate = null;

        UserDTO userDTO = adapter.toUserDTO(UserEntity.builder()
                .idUser(idUser)
                .login(login)
                .password(password)
                .username(username)
                .firstname(firstname)
                .lastname(lastname)
                .mail(mail)
                .creationDate(creationDate)
                .deleteDate(deleteDate).build());

        Assert.assertNotNull(userDTO);
        Assert.assertEquals(idUser, userDTO.getId());
        Assert.assertEquals(login, userDTO.getLogin());
        Assert.assertEquals(password, userDTO.getPassword());
        Assert.assertEquals(username, userDTO.getUsername());
        Assert.assertEquals(firstname, userDTO.getFirstname());
        Assert.assertEquals(lastname, userDTO.getLastname());
        Assert.assertEquals(mail, userDTO.getMail());
        Assert.assertEquals("", userDTO.getCreationDate());
        Assert.assertEquals("", userDTO.getDeleteDate());
    }

    @Test
    public void should_create_userEntity_from_userDTO() throws ParseException {
        Integer id = 1;
        String login = "login";
        String password = "password";
        String username = "username";
        String firstname = "firstname";
        String lastname = "lastname";
        String mail = "mail";
        String creationDate = "2000-01-01 00:00:00";
        String deleteDate = "2000-01-01 23:59:59";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        UserEntity userEntity = adapter.toUserEntity(UserDTO.builder()
                .id(id)
                .login(login)
                .password(password)
                .username(username)
                .firstname(firstname)
                .lastname(lastname)
                .mail(mail)
                .creationDate(creationDate)
                .deleteDate(deleteDate).build());

        Assert.assertNotNull(userEntity);
        Assert.assertEquals(id, userEntity.getIdUser());
        Assert.assertEquals(login, userEntity.getLogin());
        Assert.assertEquals(password, userEntity.getPassword());
        Assert.assertEquals(username, userEntity.getUsername());
        Assert.assertEquals(firstname, userEntity.getFirstname());
        Assert.assertEquals(lastname, userEntity.getLastname());
        Assert.assertEquals(mail, userEntity.getMail());
        Assert.assertEquals(format.parse(creationDate), userEntity.getCreationDate());
        Assert.assertEquals(format.parse(deleteDate), userEntity.getDeleteDate());
    }

    @Test
    public void should_create_userDTOList_from_userEntityList(){
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(UserEntity.builder()
                .idUser(1)
                .login("login")
                .password("password")
                .username("username")
                .firstname("firstname")
                .lastname("lastname")
                .mail("mail")
                .creationDate(null)
                .deleteDate(null).build());

        userEntities.add(UserEntity.builder()
                .idUser(2)
                .login("password")
                .password("login")
                .username("firstname")
                .firstname("lastname")
                .lastname("username")
                .mail("address")
                .creationDate(null)
                .deleteDate(null).build());

        List<UserDTO> userDTOS = adapter.toListUserDTO(userEntities);

        Assert.assertNotNull(userDTOS);
        Assert.assertEquals(userEntities.size(), userDTOS.size());
        Assert.assertEquals(userEntities.get(0).getIdUser(), userDTOS.get(0).getId());
        Assert.assertEquals(userEntities.get(1).getIdUser(), userDTOS.get(1).getId());
        Assert.assertEquals(userEntities.get(0).getLogin(), userDTOS.get(0).getLogin());
        Assert.assertEquals(userEntities.get(1).getLogin(), userDTOS.get(1).getLogin());
        Assert.assertEquals(userEntities.get(0).getPassword(), userDTOS.get(0).getPassword());
        Assert.assertEquals(userEntities.get(1).getPassword(), userDTOS.get(1).getPassword());
        Assert.assertEquals(userEntities.get(0).getUsername(), userDTOS.get(0).getUsername());
        Assert.assertEquals(userEntities.get(1).getUsername(), userDTOS.get(1).getUsername());
        Assert.assertEquals(userEntities.get(0).getFirstname(), userDTOS.get(0).getFirstname());
        Assert.assertEquals(userEntities.get(1).getFirstname(), userDTOS.get(1).getFirstname());
        Assert.assertEquals(userEntities.get(0).getLastname(), userDTOS.get(0).getLastname());
        Assert.assertEquals(userEntities.get(1).getLastname(), userDTOS.get(1).getLastname());
        Assert.assertEquals(userEntities.get(0).getMail(), userDTOS.get(0).getMail());
        Assert.assertEquals(userEntities.get(1).getMail(), userDTOS.get(1).getMail());
        Assert.assertEquals("", userDTOS.get(0).getCreationDate());
        Assert.assertEquals("", userDTOS.get(1).getCreationDate());
        Assert.assertEquals("", userDTOS.get(0).getDeleteDate());
        Assert.assertEquals("", userDTOS.get(1).getDeleteDate());
    }
}
