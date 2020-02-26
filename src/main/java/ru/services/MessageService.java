package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.DTO.MessageDTO;
import ru.domen.Message;
import ru.repos.MessageRepostory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    private MessageRepostory messageRepostory;

    @Transactional
    public void addMessage(Message message) {
        messageRepostory.save(message);
    }

    @Transactional
    public void deleteMassageById(Integer id) {
        messageRepostory.deleteById(id);
    }

    @Transactional
    public void deleteMessageByDialogId(Integer dialogId) {
        messageRepostory.deleteByDialogDialogId(dialogId);
    }

    public List<MessageDTO> getDialogMessages(Integer dialogId) {
        return messageRepostory.findByDialogDialogId(dialogId).stream().map(message -> MessageDTO.getMessageDTO(message)).collect(Collectors.toList());
    }
}
