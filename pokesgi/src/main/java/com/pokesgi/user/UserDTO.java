package com.pokesgi.user;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Date;

/**
 * Created by ostro on 24/03/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

    private Integer id;
    private String login;
    private String password;
    private String username;
    private String firstname;
    private String lastname;
    private String mail;
    private String creationDate;
    private String deleteDate;
}
