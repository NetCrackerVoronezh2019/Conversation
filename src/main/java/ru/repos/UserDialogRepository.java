package ru.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Dialog;
import ru.domen.UserDialog;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDialogRepository  extends CrudRepository<UserDialog,Integer> {
    List<UserDialog> findByUserIdOrderByDialogId(Integer userId);
    List<UserDialog> findByDialogIdOrderByUserId(Integer dialogId);
    void deleteByUserIdAndDialogId(Integer userId, Integer DialogId);
}
