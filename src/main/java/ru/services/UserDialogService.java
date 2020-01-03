package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.UserDialog;
import ru.repos.UserDialogRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserDialogService {

    @Autowired
    private UserDialogRepository userDialogRepository;

    public void addUserDialogRelationship(UserDialog userDialog) {
        userDialogRepository.save(userDialog);
    }

    @Transactional
    public List<UserDialog> getByUserId(Integer id) {
        return userDialogRepository.findByUserIdOrderByDialogId(id);
    }

    @Transactional
    public List<UserDialog> getByDialogId(Integer id) {
        return userDialogRepository.findByDialogIdOrderByUserId(id);
    }

    public void DeleteByUserIdAndDialogId(Integer userId,Integer dialogId) {
        userDialogRepository.deleteByUserIdAndDialogId(userId,dialogId);
    }
}
