package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.Message;
import ru.repos.MessageRepostory;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MessegeService {
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
    public List<Message> getMessageByDialogId(Integer id) {
        return messageRepostory.findByDialogIdOrderByDate(id);
    }
}
