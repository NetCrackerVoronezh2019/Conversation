package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.DTO.DialogDTO;
import ru.DTO.UserDTO;
import ru.domen.User;
import ru.services.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public UserDTO getUser(@RequestParam(name = "userId") Integer userId){
        UserDTO userDTO = userService.getUserDTOById(userId);
        return userDTO;
    }


    @GetMapping("/getUserDialogs")
    public List<DialogDTO> getDialogs(Integer userId) {
        final List<DialogDTO> dialogs = userService.getUserDTOById(userId).getDialogs();
        return dialogs;
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user) {
        userService.addUser(user);
    }
}
