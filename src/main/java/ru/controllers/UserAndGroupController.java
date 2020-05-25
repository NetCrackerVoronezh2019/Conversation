package ru.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.domen.Dialog;
import ru.domen.User;
import ru.services.DialogService;
import ru.services.DialogTypeService;
import ru.services.MessageService;
import ru.services.UserService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserAndGroupController {
    @Autowired
    private UserService userService;
    @Autowired
    private DialogService dialogService;
    @Autowired
    private DialogTypeService dialogTypeService;
    @Autowired
    private MessageService messageService;

    @PostMapping("user/createDialog")
    public Integer createDialog(@RequestParam Integer creatorId,@RequestParam Integer userId) {
        User user = userService.getUserById(userId);
        User creator = userService.getUserById(creatorId);
        List<Dialog> dialogs = user.getDialogs().stream().filter(dialog -> dialog.getType().getTypeName().equals("private")).collect(Collectors.toList());
        for (Dialog dialog :
                dialogs) {
            if (dialog.getUsers().contains(creator)) {
                return dialog.getDialogId();
            }
        }
        Dialog dialog =new Dialog();
        dialog.setCreationDate(LocalDateTime.now().plusHours(3));
        dialog.setCreatorId(creatorId);
        dialog.setName(user.getName() + " " + creator.getName());
        dialog.getUsers().add(userService.getUserById(userId));
        dialog.getUsers().add(creator);
        dialog.setType(dialogTypeService.getDialogTypeByName("private"));
        return dialogService.saveDialog(dialog).getDialogId();
    }

    @PostMapping("group/createDialog")
    public Integer createGroupDialog(@RequestParam Integer creatorId, @RequestParam String name, @RequestParam(required = false) String image) {
        Dialog dialog =new Dialog();
        dialog.setCreationDate(LocalDateTime.now().plusHours(3));
        dialog.setCreatorId(creatorId);
        dialog.setName(name);
        dialog.setType(dialogTypeService.getDialogTypeByName("group"));
        if (image != null) {
            dialog.setImage(image);
        }
        return dialogService.saveDialog(dialog).getDialogId();
    }

    @PutMapping("group/settings")
    public void groupRename(@RequestParam Integer dialogId, @RequestParam String groupName, @RequestParam(required = false) String image) {
        Dialog dialog = dialogService.getDialogById(dialogId);
        dialog.setName(groupName);
        if (image != null) {
            dialog.setImage(image);
        }
        dialogService.saveDialog(dialog);
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

    @DeleteMapping("/userAndGroup/deleteDialog/")
    public void deleteDialog(@RequestParam(name = "dialogId") Integer dialogId) {
        messageService.deleteMessageByDialogId(dialogId);
        dialogService.deleteDialogById(dialogId);
    }
}
