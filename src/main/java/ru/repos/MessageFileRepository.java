package ru.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.domen.MessageFile;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MessageFileRepository  extends CrudRepository<MessageFile,Long> {
    @Modifying
    @Query("DELETE FROM MessageFile mf where mf.fileId=:fileId")
    void delete(Long fileId);
}
