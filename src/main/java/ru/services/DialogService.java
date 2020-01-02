package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.Dialog;
import ru.repos.DialogRepository;

import javax.transaction.Transactional;

@Service
public class DialogService {

    @Autowired
    private DialogRepository dialogRepository;

    @Transactional
    public Dialog getDialogById(Long id){
        return dialogRepository.findById(id).get();
    }

    @Transactional
    public void addDialog(Dialog dialog) {
        dialogRepository.save(dialog);
    }

    @Transactional
    public void deleteDialogById(Long id) {
        dialogRepository.deleteById(id);
    }

}
