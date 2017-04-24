package com.pokesgi.user;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by ostro on 23/03/2017.
 */

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter

@Entity
@Table(name = "t_user")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Integer idUser;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    private String mail;

    @Column(nullable = false)
    private Date creationDate;

    private Date deleteDate;
}
