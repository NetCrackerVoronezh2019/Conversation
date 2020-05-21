package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.DTO.AmazonModel;
import ru.DTO.DialogDTO;
import ru.DTO.MessageDTO;
import ru.DTO.UserDTO;
import ru.domen.Dialog;
import ru.domen.Message;
import ru.domen.MessageFile;
import ru.domen.User;
import ru.kafka.Microservices;
import ru.services.*;

import java.util.Comparator;
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
    private NotificationService notificationService;
    @Autowired
    private DialogTypeService dialogTypeService;
    @Autowired
    private MessageFileService messageFileService;
    @Autowired
    private Microservices microservices;

    @PostMapping("/dialogCreate/")
    public void createDialog(@RequestBody DialogDTO dialogDTO) {
        Dialog dialog = new Dialog();
        dialog.setName(dialogDTO.getName());
        dialog.setCreatorId(dialogDTO.getCreatorId());
        dialog.setType(dialogTypeService.getDialogTypeByName(dialogDTO.getType()));
        dialog.setCreationDate(new Date());
        dialog.setType(dialogTypeService.getDialogTypeByName("public"));
        dialog = dialogService.saveDialog(dialog);
        if (dialogDTO.getImage()!= null) {
            String key = "dialog_" + dialog.getDialogId() + "_avatar";
            AmazonModel amazonModel = new AmazonModel(key, dialogDTO.getImage());
            dialog.setImage(key);
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<AmazonModel> amazonModelHttpEntity = new HttpEntity<>(amazonModel);
            restTemplate.exchange("http://192.168.99.103:"+ microservices.getAmazonPort() +"/dialog/uploadFile", HttpMethod.POST, amazonModelHttpEntity, Object.class);
            dialogService.saveDialog(dialog);
        }
    }

    @PutMapping("dialog/settings")
    public void dialogSettings(@RequestBody DialogDTO dialogDTO) {
        Dialog dialog = dialogService.getDialogById(dialogDTO.getDialogId());
        dialog.setName(dialogDTO.getName());
        dialogService.saveDialog(dialog);
    }

    @PutMapping("dialog/setAvatar")
    public void dialogSetAvarar(@RequestBody DialogDTO dialogDTO) {
        Dialog dialog = dialogService.getDialogById(dialogDTO.getDialogId());
        if (dialog.getImage()== null) {
            dialog.setImage("dialog_" + dialog.getDialogId() + "_avatar");
            dialogService.saveDialog(dialog);
        }
        AmazonModel amazonModel = new AmazonModel(dialog.getImage(),dialogDTO.getImage());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AmazonModel> amazonModelHttpEntity = new HttpEntity<>(amazonModel);
        restTemplate.exchange("http://192.168.99.103:"+ microservices.getAmazonPort() +"/dialog/uploadFile", HttpMethod.POST,amazonModelHttpEntity,Object.class);
    }

    @PutMapping("dialog/setMessage")
    public void setMessage(@RequestBody MessageDTO messageDTO) {
        Message message = messageService.getById(messageDTO.getMessageId());
        message.setText(messageDTO.getText());
        message.setModified(true);
        messageService.addMessage(message);
    }

    @GetMapping("/getDialogMembers/")
    public List<UserDTO> getDialogs(@RequestParam(name = "dialogId") Integer dialogId,@RequestParam Integer userId) {
        final List<UserDTO> users = dialogService.getDialogDTOById(dialogId).getUsers();
        return users;
    }

    @GetMapping("/getDialogMessages/")
    public List<MessageDTO> getMessages(@RequestParam(name = "dialogId") Integer dialogId, @RequestParam Integer userId) {
        return messageService.getDialogMessages(dialogId).stream().sorted(Comparator.comparing(MessageDTO::getDate)).collect(Collectors.toList());
    }

    @GetMapping("/getDialog/")
    public DialogDTO getDialogInfo(@RequestParam(name = "dialogId") Integer dialogId,@RequestParam Integer userId) {
        DialogDTO dialog = dialogService.getDialogDTOById(dialogId);
        User user = userService.getUserById(userId);
        dialog.setCountNotification(user.getNotifications().stream().filter(notification -> notification.getMessage().getDialog().getDialogId() == dialogId).count());
        return dialog;
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
        message.setModified(false);
        message.setReadBySomebodey(false);
        message.setDate(new Date());
        message = messageService.addMessage(message);
        for (int i =0; i< messageDTO.getFiles().size();i++) {
            String key = "Message_" + message.getMessageId() + "+file_" + i;
            AmazonModel amazonModel = new AmazonModel(key,messageDTO.getFiles().get(i));
            MessageFile messageFile = new MessageFile();
            messageFile.setFile(key);
            messageFile.setName(messageDTO.getNames().get(i));
            messageFile.setMessage(message);
            messageFileService.saveMessageFile(messageFile);
            message.getFiles().add(messageFile);
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<AmazonModel> amazonModelHttpEntity = new HttpEntity<>(amazonModel);
            restTemplate.exchange("http://192.168.99.103:"+ microservices.getAmazonPort() +"/dialog/uploadFile", HttpMethod.POST,amazonModelHttpEntity,Object.class);
        }
        notificationService.addNotification(message);
        return MessageDTO.getMessageDTO(message);
    }
}