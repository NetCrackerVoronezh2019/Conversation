package ru.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Message;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MessageRepostory extends CrudRepository<Message,Integer> {

    public List<Message> findByDialogDialogId(Integer dialogId);
    @Query("DELETE FROM Message m WHERE m.dialog.dialogId = ?1")
    public void deleteByDialogDialogId(Integer dialogId);
}
