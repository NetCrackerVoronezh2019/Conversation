package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.domen.Dialog;
import ru.domen.Message;
import ru.domen.UserDialog;
import ru.services.DialogService;
import ru.services.MessageService;
import ru.services.UserDialogService;
import ru.services.UserService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DialogController {

    @Autowired
    private DialogService dialogService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserDialogService userDialogService;
    @Autowired
    private UserService userService;

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/Dialogs")
    public List<Dialog> Dialogs(Model model, @RequestParam(name ="userId") Integer userId) {
        return userDialogService.getByUserId(userId).stream().map(UserDialog::getDialogId).map(di -> dialogService.getDialogById(di)).collect(Collectors.toList());
    }

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/chat/{dialogId}/massages")
    public String chatMassages(@PathVariable Integer dialogId) {
        return messageService.getMessageByDialogId(dialogId).stream().
                map(message -> message.getSenderId()+ ':' + message.getText()+ ':' + message.getDate()).toString();
    }

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/chat/{dialogId}/users")
    public String chatUsers(@PathVariable Integer dialogId) {
        return userDialogService.getByDialogId(dialogId).stream().
                map(UserDialog::getUserId).
                map(userId -> userService.getUserById(userId)).toString();
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/sendMassage")
    public void sendMassage(@RequestParam Integer dialogId, @RequestParam Integer userId,@RequestParam String text) {
        Message message = new Message(text,new Date(),dialogId,userId);
        messageService.addMessage(message);
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/chatCreate/")
    public void createDialog(@RequestParam Integer userId, @RequestParam String name) {
        Dialog dialog = new Dialog(name, new Date(), userId);
        dialogService.addDialog(dialog);
    }
}