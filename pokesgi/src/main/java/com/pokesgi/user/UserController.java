package com.pokesgi.user;

import com.pokesgi.Adapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final Adapter adapter;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
        this.adapter = new Adapter();
    }

    @GetMapping
    public String homePage(){
        return "Hello World !";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("error validation");
            throw new CreationFailedException();
        }

        try {
            userService.createUser(adapter.toUserEntity(userDTO));
        } catch (ParseException e) {
            throw new CreationFailedException();
        }
    }
}
