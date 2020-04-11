package ru.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.domen.Dialog;
import ru.domen.User;
import ru.services.DialogService;
import ru.services.DialogTypeService;
import ru.services.UserService;

import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:8090")
public class UserAndGroupController {
    @Autowired
    private UserService userService;
    @Autowired
    private DialogService dialogService;
    @Autowired
    private DialogTypeService dialogTypeService;
    @PostMapping("user/createDialog")
    public Integer createDialog(@RequestParam Integer creatorId,@RequestParam Integer userId) {
        Dialog dialog =new Dialog();
        dialog.setCreationDate(new Date());
        dialog.setCreatorId(creatorId);
        User user = userService.getUserById(userId);
        User creator = userService.getUserById(creatorId);
        dialog.setName(user.getName() + " " + creator.getName());
        dialog.getUsers().add(userService.getUserById(userId));
        return dialogService.addDialog(dialog).getDialogId();
    }

    @PostMapping("group/createDialog")
    public Integer createGroupDialog(@RequestParam Integer creatorId, @RequestParam String name) {
        Dialog dialog =new Dialog();
        dialog.setCreationDate(new Date());
        dialog.setCreatorId(creatorId);
        dialog.setName(name);
        dialog.setType(dialogTypeService.getDialogTypeByName("group"));
        return dialogService.addDialog(dialog).getDialogId();
    }

    @PostMapping("group/subscribeDialog")
    public void subscribe(@RequestParam Integer userId,@RequestParam Integer dialogId) {
        dialogService.addUserInDialog(dialogService.getDialogById(dialogId),userService.getUserById(userId));
    }

    @DeleteMapping("group/leaveDialog/")
    public void liveDialog(@RequestParam(name = "userId") Integer userId,@RequestParam(name = "dialogId") Integer dialogId) {
        Dialog dialog = dialogService.getDialogById(dialogId);
        dialogService.deleteUserFromDialog(userId,dialog);
    }
}
