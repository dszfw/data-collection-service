package dao;

import models.Record;
import play.db.jpa.*;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class RecordDAO {
    @Transactional
    public List<Record> findAll() {
        List<Record> records = JPA.em().createQuery("select r from Record r").getResultList();
        return records;
    }

}
