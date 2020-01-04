package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.services.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/UserName")
    public String userName(@RequestParam(name = "userId") Integer userId){
        return userService.getUserById(userId).getName();
    }
}
