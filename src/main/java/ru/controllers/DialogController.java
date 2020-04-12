package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.DTO.DialogDTO;
import ru.DTO.MessageDTO;
import ru.DTO.UserDTO;
import ru.domen.Dialog;
import ru.domen.Message;
import ru.domen.User;
import ru.services.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9080")
public class DialogController {

    @Autowired
    private DialogService dialogService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private DialogTypeService dialogTypeService;

    @PostMapping("/dialogCreate/")
    public void createDialog(@RequestBody DialogDTO dialogDTO) {
        Dialog dialog = new Dialog();
        dialog.setName(dialogDTO.getName());
        dialog.setCreatorId(dialogDTO.getCreatorId());
        dialog.setType(dialogTypeService.getDialogTypeByName(dialogDTO.getType()));
        dialog.setCreationDate(new Date());
        dialog.setType(dialogTypeService.getDialogTypeByName("public"));
        dialogService.saveDialog(dialog);
    }

    @GetMapping("/getDialogMembers/")
    public List<UserDTO> getDialogs(@RequestParam(name = "dialogId") Integer dialogId,@RequestParam Integer userId) {
        final List<UserDTO> users = dialogService.getDialogDTOById(dialogId).getUsers();
        return users;
    }

    @GetMapping("/getDialogMessages/")
    public List<MessageDTO> getMessages(@RequestParam(name = "dialogId") Integer dialogId, @RequestParam Integer userId) {
        return messageService.getDialogMessages(dialogId);
    }

    @GetMapping("/getDialog/")
    public DialogDTO getDialogInfo(@RequestParam(name = "dialogId") Integer dialogId,@RequestParam Integer userId) {
        return dialogService.getDialogDTOById(dialogId);
    }

    @DeleteMapping("/liveDialog/")
    public void liveDialog(@RequestParam(name = "userId") Integer userId,@RequestParam(name = "dialogId") Integer dialogId) {
        Dialog dialog = dialogService.getDialogById(dialogId);
        dialogService.deleteUserFromDialog(userId,dialog);
        if (dialog.getUsers().size() == 0) {
            dialogService.deleteDialogById(dialogId);
        }
    }

    @DeleteMapping("/deleteDialog/")
    public void deleteDialog(@RequestParam(name = "dialogId") Integer dialogId) {
        messageService.deleteMessageByDialogId(dialogId);
        dialogService.deleteDialogById(dialogId);
    }

    @GetMapping("/addUserInDialog/")
    public void addUserInDialog(@RequestParam(name = "userId" ) Integer userId, @RequestParam(name = "dialogId") Integer dialogId, @RequestParam Integer adderId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            Dialog dialog = dialogService.getDialogById(dialogId);
            if (!dialog.getUsers().contains(user)) {
                dialogService.addUserInDialog(dialog, user);
            }
        }
    }


    @PostMapping("/sendMessage/")
    public MessageDTO sendMessage(@RequestBody MessageDTO messageDTO) {
        Message message = new Message();
        message.setDialog(dialogService.getDialogById(messageDTO.getDialog()));
        message.setSender(userService.getUserById(messageDTO.getSender().getUserId()));
        message.setText(messageDTO.getText());
        message.setModified(messageDTO.isModified());
        message.setDate(new Date());
        messageService.addMessage(message);
        notificationService.addNotification(message);
        return new MessageDTO(message);
    }
}