package ru.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.domen.Notification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface NotificationRepository extends CrudRepository<Notification,Integer> {

    List<Notification> findByUserUserId(Integer userId);
    List<Notification> findByMessageMessageId(Integer messageId);

    @Modifying
    @Query("DELETE FROM Notification N WHERE N.message.messageId in (select m.messageId from Message m where m.dialog.dialogId =:dialogId) and N.user.userId =:userId")
    void deleteByMessageDialogDialogIdAndUserUserId(@Param("dialogId")Integer dialogId,@Param("userId") Integer userId);

    List<Notification> findByUserUserIdAndMessageDialogDialogId(Integer userId, Integer dialogId);
}
