package ru.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.Message;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MessegeRepostory extends CrudRepository<Message,Long> {
    List<Message> findByDialogIdOrderByDate(Long dialogId);
}
