package ru.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
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
    void deleteByMessageDialogDialogIdAndUserUserId(Integer dialogId,Integer userId);
}
