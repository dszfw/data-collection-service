package controllers;

import dao.FieldDAO;
import models.Field;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

@Singleton
public class FieldController {

    @Inject
    FieldDAO fieldDAO;

    @Transactional
    public Result get(Long id) {
        Field field = fieldDAO.find(id);
        if (field == null) {
            return badRequest();
        }
        return ok(Json.toJson(field));
    }

    @Transactional
    public Result getAll() {
        List<Field> fields = fieldDAO.findAll();
        return ok(Json.toJson(fields));
    }

    @Transactional
    public Result post() {
        Form<Field> fieldForm = Form.form(Field.class).bindFromRequest();
        if (fieldForm.hasErrors()) {
            return badRequest(fieldForm.errorsAsJson());
        }
        // TODO DAO method save
        JPA.em().persist(fieldForm.get());
        return ok();
    }

    @Transactional
    public Result delete(Long id) {
        // TODO dao get
        Field field = JPA.em().find(Field.class, id);
        if (field == null) {
            return badRequest();
        }
        // TODO dao method remove
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
        // TODO dao update
        JPA.em().merge(field);
        return ok();
    }

}
