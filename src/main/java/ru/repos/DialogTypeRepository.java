package ru.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.DialogType;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DialogTypeRepository  extends CrudRepository<DialogType,Integer> {
    public DialogType findByTypeName(String typeName);
}
