package com.pokesgi;

import com.pokesgi.user.UserDTO;
import com.pokesgi.user.UserEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ostro on 24/03/2017.
 */
public class Adapter {

    public UserEntity toUserEntity(UserDTO userDTO) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date deleteDate = userDTO.getDeleteDate().isEmpty() ? null : format.parse(userDTO.getDeleteDate());

        return UserEntity.builder().idUser(userDTO.getId())
                .login(userDTO.getLogin())
                .password(userDTO.getPassword())
                .username(userDTO.getUsername())
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .mail(userDTO.getMail())
                .creationDate(format.parse(userDTO.getCreationDate()))
                .deleteDate(deleteDate)
                .build();
    }

    public UserDTO toUserDTO(UserEntity userEntity){
        return UserDTO.builder().id(userEntity.getIdUser())
                .login(userEntity.getLogin())
                .password(userEntity.getPassword())
                .username(userEntity.getUsername())
                .firstname(userEntity.getFirstname())
                .lastname(userEntity.getLastname())
                .mail(userEntity.getMail())
                .creationDate(userEntity.getCreationDate().toString())
                .deleteDate(userEntity.getDeleteDate().toString())
                .build();
    }
}
