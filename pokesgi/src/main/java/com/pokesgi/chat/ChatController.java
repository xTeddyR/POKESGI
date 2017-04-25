package com.pokesgi.chat;

import com.pokesgi.user.UserController;
import com.pokesgi.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * Created by ostro on 03/04/2017.
 */
@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public MessageDisplayer displayer(SimpMessageHeaderAccessor headerAccessor, MessageDTO message) throws InterruptedException {
        Thread.sleep(1000);
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
        return new MessageDisplayer(((UserDTO) sessionAttributes.get("currentUser")).getUsername() +
                " " + message.getMessage());
    }
}
