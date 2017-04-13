package com.pokesgi.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by ostro on 03/04/2017.
 */
@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public MessageDisplayer displayer(MessageDTO message) throws InterruptedException {
        Thread.sleep(1000);
        return new MessageDisplayer(message.getName() + " : " + message.getMessage());
    }
}
