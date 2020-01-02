package ru.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Dialog;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DialogRepository extends CrudRepository<Dialog,Long> {

}
