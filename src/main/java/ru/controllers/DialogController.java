package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ru.DTO.DialogDTO;
import ru.DTO.MessageDTO;
import ru.DTO.UserDTO;
import ru.domen.Dialog;
import ru.domen.Message;
import ru.domen.User;
import ru.services.DialogService;
import ru.services.MessageService;
import ru.services.UserService;

import javax.persistence.criteria.CriteriaBuilder;
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
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/dialogCreate/")
    @CrossOrigin(origins="http://localhost:8080")
    public void createDialog(@RequestBody Dialog dialog) {
        dialog.setCreationDate(new Date());
        dialogService.addDialog(dialog);
    }

    @GetMapping("/getDialogMembers/")
    @CrossOrigin(origins="http://localhost:8080")
    public List<UserDTO> getDialogs(@RequestParam(name = "dialogId") Integer dialogId,@RequestParam Integer userId) {
        final List<UserDTO> users = dialogService.getDialogDTOById(dialogId).getUsers();
        return users;
    }

    @GetMapping("/getDialogMessages/")
    @CrossOrigin(origins="http://localhost:8080")
    public List<MessageDTO> getMessages(@RequestParam(name = "dialogId") Integer dialogId, @RequestParam Integer userId) {
        return messageService.getDialogMessages(dialogId);
    }

    @GetMapping("/getDialog/")
    @CrossOrigin(origins="http://localhost:8080")
    public DialogDTO getDialogInfo(@RequestParam(name = "dialogId") Integer dialogId,@RequestParam Integer userId) {
        return dialogService.getDialogDTOById(dialogId);
    }

    @MessageMapping("/sendMessage/")
    @CrossOrigin(origins="http://localhost:4200")
    @SendTo("/dialog/{messageDTO.getDialog}")
    public void sendMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setDate(new Date());
        message.setModified(messageDTO.isModified());
        message.setText(messageDTO.getText());
        message.setSender(userService.getUserById(messageDTO.getSender().getUserId()));
        message.setDialog(dialogService.getDialogById(messageDTO.getDialog()));
        messageService.addMessage(message);
        template.convertAndSend("/dialog/" + messageDTO.getDialog(),MessageDTO.getMessageDTO(message));
    }

    @CrossOrigin(origins="http://localhost:8080")
    @DeleteMapping("/liveDialog/")
    public void liveDialog(@RequestParam(name = "userId") Integer userId,@RequestParam(name = "dialogId") Integer dialogId) {
        Dialog dialog = dialogService.getDialogById(dialogId);
        dialogService.deleteUserFromDialog(userId,dialog);
        if (dialog.getUsers().size() == 0) {
            dialogService.deleteDialogById(dialogId);
        }
    }

    @CrossOrigin(origins="http://localhost:8080")
    @DeleteMapping("/deleteDialog/")
    public void deleteDialog(@RequestParam(name = "dialogId") Integer dialogId) {
        messageService.deleteMessageByDialogId(dialogId);
        dialogService.deleteDialogById(dialogId);
    }

    @CrossOrigin(origins="http://localhost:8080")
    @GetMapping("/addUserInDialog/")
    public void addUserInDialog(@RequestParam(name = "userName" ) String userName, @RequestParam(name = "dialogId") Integer dialogId, @RequestParam Integer adderId) {
        User user = userService.getUserByName(userName);
        if (user != null) {
            Dialog dialog = dialogService.getDialogById(dialogId);
            if (!dialog.getUsers().contains(user)) {
                dialogService.addUserInDialog(dialog, user);
            }
        }
    }
}