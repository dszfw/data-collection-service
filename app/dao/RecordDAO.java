package dao;

import models.Record;
import play.db.jpa.*;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class RecordDAO {
    @Transactional
    public Record find(Long id) {
        Record record = JPA.em().find(Record.class, id);
        return record;
    }

    @Transactional
    public List<Record> findAll() {
        List<Record> records = JPA.em().createQuery("from Record", Record.class).getResultList();
        return records;
    }

    @Transactional
    public void save(Record record) {
        JPA.em().persist(record);
    }

}
