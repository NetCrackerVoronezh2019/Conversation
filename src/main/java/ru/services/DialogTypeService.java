package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.DialogType;
import ru.repos.DialogTypeRepository;

@Service
public class DialogTypeService {
    @Autowired
    private DialogTypeRepository dialogTypeRepository;

    public DialogType getDialogTypeByName(String name) {
        return dialogTypeRepository.findByTypeName(name);
    }

    public DialogType saveDialogType(DialogType dialogType) {
        return dialogTypeRepository.save(dialogType);
    }

    public void deleteDialogType(DialogType dialogType) {
        dialogTypeRepository.delete(dialogType);
    }
}
