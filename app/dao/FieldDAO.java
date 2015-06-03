package dao;

import com.google.inject.Singleton;
import models.Field;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import java.util.List;

@Singleton
public class FieldDAO {
    @Transactional
    public Field find(Long id) {
        Field field = JPA.em().find(Field.class, id);
        return field;
    }

    @Transactional
    public List<Field> findAll() {
        List<Field> fields = JPA.em().createQuery("select f from Field f").getResultList();
        return fields;
    }
}
