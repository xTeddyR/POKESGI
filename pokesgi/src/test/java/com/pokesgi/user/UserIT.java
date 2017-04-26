package com.pokesgi.user;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.mapper.ObjectMapperType;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.*;

/**
 * Created by ostro on 17/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIT {

    @LocalServerPort
    private int localServerPort;

    @Before
    public void init(){
        RestAssured.port = localServerPort;
    }

    @Test
    public void should_sign_up_user(){
        UserDTO userDTO = UserDTO.builder()
                .id(1)
                .login("login")
                .password("password")
                .username("Testeur")
                .firstname("")
                .lastname("")
                .creationDate("2017-04-20 00:00:00")
                .build();

        given()
            .contentType(ContentType.JSON)
            .body(userDTO)
        .when()
            .post("/inscription")
        .then()
            .statusCode(201);
    }

    @Test
    public void should_not_sign_up_user(){
        UserDTO userDTO = UserDTO.builder()
                .id(2)
                .username("Testeur")
                .firstname("")
                .lastname("")
                .creationDate("2017-04-20 00:00:00")
                .build();

        given()
            .contentType(ContentType.JSON)
            .body(userDTO)
        .when()
            .post("/inscription")
        .then()
            .statusCode(400);
    }

    @Test
    public void should_connect_user(){

        given()
            .param("login", "login")
            .param("password", "password")
        .when()
            .post("/connexion")
        .then()
            .header("location", "http://localhost:8080/chat.html")
            .statusCode(301);
    }

    @Test
    public void should_not_connect_user(){

        given()
            .param("login", "password")
            .param("password", "login")
        .when()
            .post("/connexion")
        .then()
            .log().all()
            .header("location", "http://localhost:8080/index.html")
            .statusCode(301);
    }

    @Test
    public void should_get_all_users(){
        when()
            .get("/users")
        .then()
            .body("$", IsCollectionWithSize.hasSize(2));
    }
}
