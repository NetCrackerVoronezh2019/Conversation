package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.Message;
import ru.repos.MessegeRepostory;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MessegeService {
    @Autowired
    private MessegeRepostory messegeRepostory;

    @Transactional
    public void addMessage(Message message) {
        messegeRepostory.save(message);
    }

    @Transactional
    public void deleteMassageById(Long id) {
        messegeRepostory.deleteById(id);
    }

    @Transactional
    public List<Message> getMessageByDialogId(Long id) {
        return messegeRepostory.findByDialogIdOrderByDate(id);
    }
}
