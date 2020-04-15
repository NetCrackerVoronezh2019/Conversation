package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.DTO.DialogDTO;
import ru.DTO.UserDTO;
import ru.domen.DialogType;
import ru.domen.Notification;
import ru.domen.User;
import ru.domen.Dialog;
import ru.services.DialogTypeService;
import ru.services.UserService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DialogTypeService dialogTypeService;

    @GetMapping("/getUser")
    public UserDTO getUser(@RequestParam(name = "userId") Integer userId){
        UserDTO userDTO = userService.getUserDTOById(userId);
        return userDTO;
    }


    @GetMapping("/getUserDialogs")
    public List<DialogDTO> getDialogs(@RequestParam Integer userId, @RequestParam(required = false) String type) {
        List<Dialog> dialogs = userService.getUserById(userId).getDialogs();
        if (type !=null) {
            DialogType dialogType = dialogTypeService.getDialogTypeByName(type);
            dialogs = dialogs.stream().filter(dialog -> dialog.getType().equals(dialogType)).collect(Collectors.toList());
        }
        List<DialogDTO> dialogsDTO = new ArrayList<>();
        List<Notification> notifications = userService.getUserById(userId).getNotifications();
        for (Dialog dg:
             dialogs) {
            DialogDTO dialogDTO = DialogDTO.getDialogDTO(dg);
            dialogDTO.setCountNotification(notifications.stream().filter(notification -> notification.getMessage().getDialog().getDialogId() == dg.getDialogId()).count());
            dialogsDTO.add(dialogDTO);
        }
        dialogsDTO = dialogsDTO.stream().sorted(Comparator.comparing(DialogDTO::getLastMessageDate).reversed()).collect(Collectors.toList());
        return dialogsDTO;
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setUserId(userDTO.getUserId());
        userService.addUser(user);
    }

}
