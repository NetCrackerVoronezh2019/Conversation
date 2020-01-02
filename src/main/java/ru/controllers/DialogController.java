package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.domen.Dialog;
import ru.domen.Message;
import ru.domen.UserDialog;
import ru.repos.DialogRepository;
import ru.repos.MessageRepostory;
import ru.repos.UserDialogRepository;
import ru.repos.UserRepository;

import java.util.Date;

@RestController
public class DialogController {

    @Autowired
    private DialogRepository dialogRepository;
    @Autowired
    private MessageRepostory messageRepostory;
    @Autowired
    private UserDialogRepository userDialogRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/chat/{dialogId}/massages")
    public String chatMassages(@PathVariable long dialogId) {
        return messageRepostory.findByDialogIdOrderByDate(dialogId).stream().
                map(message -> message.getSenderId()+ ':' + message.getText()+ ':' + message.getDate()).toString();
    }

    @GetMapping("/chat/{dialogId}/users")
    public String chatUsers(@PathVariable long dialogId) {
        return userDialogRepository.findByDialogIdOrderByUserId(dialogId).stream().
                map(UserDialog::getUserId).
                map(userId -> userRepository.findById(userId).get()).toString();
    }

    @PostMapping("/sendMassage")
    public void sendMassage(@RequestParam long dialogId, @RequestParam long userId,@RequestParam String text) {
        Message message = new Message(text,new Date(),dialogId,userId);
        messageRepostory.save(message);
    }

    @PostMapping("/chatCreate/")
    public void createDialog(@RequestParam long userId, @RequestParam String name) {
        Dialog dialog = new Dialog(name, new Date(), userId);
        dialogRepository.save(dialog);
    }
}