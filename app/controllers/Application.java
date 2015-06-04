package controllers;

import dao.FieldDAO;
import dao.RecordDAO;
import models.Field;
import models.Record;
import play.mvc.*;
import play.db.jpa.*;
import views.html.*;
import play.data.Form;

import javax.inject.Inject;
import java.util.List;

public class Application extends Controller {

    @Inject
    FieldDAO fieldDAO;
    @Inject
    RecordDAO recordDAO;

    @Transactional
    public Result index() {
        // TODO inactive
        List<Field> fieldList = fieldDAO.findAllActive();
        return ok(index.render(fieldList));
    }

    // ---------------------- FIELDS ---------------------------------------------
    public Result allFieldsPage() {
        return ok(fields.render());
    }

    @Transactional
    public Result fieldByIdPage(Long id) {
        // TODO get through api
        Field field = JPA.em().find(Field.class, id);
        Form<Field> form = Form.form(Field.class);
        return ok(createEditField.render(form.fill(field), "edit"));
    }

    public Result createFieldPage() {
        return ok(createEditField.render(Form.form(Field.class), "create"));
    }

    // ---------------------- RESPONSES ------------------------------------------
    @Transactional
    public Result responsesPage() {
        List<Field> fields = fieldDAO.findAllActive();
        List<Record> records = recordDAO.findAll();
        return ok(responses.render(records, fields));
    }

}
