package controllers;

import models.Field;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class FieldController {

    @Transactional
    public Result get(Long id) {
        // #TODO
//        List<Field> fields = JPA.em().createQuery("select f from Field f").getResultList();
        return ok("#todo");
    }

    @Transactional
    public Result getAll() {
        List<Field> fields = JPA.em().createQuery("select f from Field f").getResultList();
        return ok(Json.toJson(fields));
    }

    @Transactional
    public Result post() {
        Form<Field> fieldForm = Form.form(Field.class).bindFromRequest();
        if (fieldForm.hasErrors()) {
            return badRequest(fieldForm.errorsAsJson());
        }
        JPA.em().persist(fieldForm.get());
        return ok();
    }

    @Transactional
    public Result delete(Long id) {
        Field field = JPA.em().find(Field.class, id);
        if (field == null) {
            return badRequest();
        }
        JPA.em().remove(field);
        return ok();
    }

    @Transactional
    public Result put(Long id) {
        Form<Field> fieldForm = Form.form(Field.class).bindFromRequest();
        if (fieldForm.hasErrors()) {
            return badRequest(fieldForm.errorsAsJson());
        }
        Field field = fieldForm.get();
        field.id = id;
        JPA.em().merge(field);
        return ok();
    }

}
