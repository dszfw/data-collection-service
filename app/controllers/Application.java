package controllers;

import models.Field;
import models.FieldData;
import models.FieldType;
import models.Record;
import play.mvc.*;
import play.db.jpa.*;
import views.html.*;
import play.data.Form;

import java.util.Arrays;

public class Application extends Controller {

    public Result index() {
        return ok(index.render());
    }

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

    // TODO Should be removed
    @Transactional
    public Result testField() {
        Field field = new Field();
        field.active = true;
        field.aLabel = "label";
        field.fieldType = FieldType.COMBO_BOX;
        field.options = Arrays.asList("one", "two", "three");
        field.required = true;
        JPA.em().persist(field);
        return ok();
    }

}
