package ru.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Dialog;
import ru.domen.UserDialog;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDialogRepository  extends CrudRepository<UserDialog,Long> {
    List<UserDialog> findByUserIdOrderByDialogId(Long userId);
    List<UserDialog> findByDialogIdOrderByUserId(Long dialogId);
    void deleteByUserIdAndDialogId(Long userId, Long DialogId);
}
