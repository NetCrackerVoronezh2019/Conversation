package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.DTO.DialogDTO;
import ru.DTO.UserDTO;
import ru.services.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public UserDTO getUser(@RequestParam(name = "userId") Integer userId){
        return userService.getUserDTOById(userId);
    }

    @GetMapping("/getUserDialogs")
    public List<DialogDTO> getDialogs(Integer userId) {
        final List<DialogDTO> dialogs = userService.getUserDTOById(userId).getDialogs();
        return dialogs;
    }
}
