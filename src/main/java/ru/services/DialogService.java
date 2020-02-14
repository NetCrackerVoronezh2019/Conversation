package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.DTO.DialogDTO;
import ru.domen.Dialog;
import ru.domen.User;
import ru.repos.DialogRepository;
import ru.repos.UserRepository;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class DialogService {

    @Autowired
    private DialogRepository dialogRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public DialogDTO getDialogDTOById(Integer id){
        return DialogDTO.getDialogDTO(dialogRepository.findById(id).get());
    }

    @Transactional
    public Dialog getDialogById(Integer id){
        return dialogRepository.findById(id).get();
    }

    @Transactional
    public void addDialog(Dialog dialog) {
        dialog.getUsers().add(userRepository.findById(dialog.getCreatorId()).get());
        dialogRepository.save(dialog);
    }

    @Transactional
    public void deleteDialogById(Integer id) {
        dialogRepository.deleteById(id);
    }

    @Transactional
    public void addUserInDialog(Dialog dialog,User user) {
        dialog.getUsers().add(user);
        dialogRepository.save(dialog);
    }

    public void deleteUserFromDialog(Integer userId,Dialog dialog){
        dialog.setUsers(dialog.getUsers().stream().filter(user -> (user.getUserId() != userId)).collect(Collectors.toList()));
        dialogRepository.save(dialog);
    }
}
