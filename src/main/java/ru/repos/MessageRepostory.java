package ru.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.domen.Message;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MessageRepostory extends CrudRepository<Message,Integer> {

    public List<Message> findByDialogDialogIdOrderByDate(Integer dialogId);

    @Modifying
    @Query("DELETE FROM Message m WHERE m.dialog.dialogId =:dialogId")
    public void deleteByDialogDialogId(@Param("dialogId") Integer dialogId);
}
