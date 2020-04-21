package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.MessageFile;
import ru.repos.MessageFileRepository;

@Service
public class MessageFileService {
    @Autowired
    private MessageFileRepository messageFileRepository;


    public MessageFile saveMessageFile(MessageFile messageFile) {
        return messageFileRepository.save(messageFile);
    }

    public void deleteMessageFile(Long id) {
        messageFileRepository.delete(id);
    }
}
