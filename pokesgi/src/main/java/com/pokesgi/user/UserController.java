package com.pokesgi.user;

import com.pokesgi.Adapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final Adapter adapter;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
        this.adapter = new Adapter();
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return adapter.toListUserDTO(userService.getAllUsers());
    }

    @GetMapping("/deconnexion")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public String disconnectUser(HttpSession session){
        session.removeAttribute("currentUser");

        return "redirect:http://localhost:8080/index.html";
    }

    @PostMapping("/connexion")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public String canConnect(HttpSession session, @RequestParam(value = "login") String login,
                             @RequestParam(value = "password") String password){

        UserEntity userEntity = userService.canConnect(login, password);

        if (null != userEntity){
            session.setAttribute("currentUser", adapter.toUserDTO(userEntity));
            return "redirect:http://localhost:8080/chat.html";
        }
        return "redirect:http://localhost:8080/index.html";
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
