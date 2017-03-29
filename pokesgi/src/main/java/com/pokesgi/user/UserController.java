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
    private static UserDTO currentUser;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
        this.adapter = new Adapter();
    }

    @GetMapping
    public String homePage(){
        return "Hello World !";
    }

    @GetMapping("/deconnexion")
    public boolean disconnectUser(){
        currentUser = null;

        return currentUser == null ? true : false;
    }

    @PostMapping
    public boolean canConnect(@RequestParam(value = "login") String login,
                              @RequestParam(value = "password") String password){

        currentUser = adapter.toUserDTO(userService.canConnect(login, password));

        return currentUser != null ? true : false;
    }

    @PostMapping("/inscription")
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
