package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.domen.Dialog;
import ru.domen.Message;
import ru.services.DialogService;
import ru.services.MessageService;
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
    private UserService userService;


    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/chatCreate/")
    public void createDialog(@RequestParam Integer userId, @RequestParam String name) {
        Dialog dialog = new Dialog(name, new Date(), userId);
        dialogService.addDialog(dialog);
    }
}