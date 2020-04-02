package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.DTO.DialogDTO;
import ru.DTO.UserDTO;
import ru.domen.Notification;
import ru.domen.User;
import ru.domen.Dialog;
import ru.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
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
        List<Dialog> dialogs = userService.getUserById(userId).getDialogs();
        List<DialogDTO> dialogsDTO = new ArrayList<>();
        List<Notification> notifications = userService.getUserById(userId).getNotifications();
        for (Dialog dg:
             dialogs) {
            DialogDTO dialogDTO = DialogDTO.getDialogDTO(dg);
            dialogDTO.setCountNotification(notifications.stream().filter(notification -> notification.getMessage().getDialog().getDialogId() == dg.getDialogId()).count());
            dialogsDTO.add(dialogDTO);
        }
        return dialogsDTO;
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user) {
        userService.addUser(user);
    }

}
