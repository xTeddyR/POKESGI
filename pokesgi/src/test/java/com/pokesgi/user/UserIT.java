package com.pokesgi.user;

import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.when;

/**
 * Created by ostro on 17/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIT {

    private SockJsClient sockJsClient;
    private WebSocketStompClient webSocketStompClient;

    @Test
    public void should_get_all_users(){
        when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("$", IsCollectionWithSize.hasSize(0));
    }

    @Test
    public void should_connect_user(){
        when()
            .post("/connexion")
        .then()
            .statusCode(200)
            .body("$", IsCollectionWithSize.hasSize(0));
    }

    @Test
    public void should_not_connect_user(){
        when()
            .post("/connexion")
        .then()
            .statusCode(200)
            .body("$", IsCollectionWithSize.hasSize(0));
    }
}
