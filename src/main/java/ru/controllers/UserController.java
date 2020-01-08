package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.domen.User;
import ru.domen.Dialog;
import ru.services.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public User getUser(@RequestParam(name = "userId") Integer userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/getUserDialogs")
    public List<Dialog> getDialogs(Integer userId) {
        return userService.getUserById(userId).getDialogs();
    }
}
