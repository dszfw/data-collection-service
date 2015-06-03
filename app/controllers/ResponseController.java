package controllers;

import dao.RecordDAO;
import models.Record;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

import static play.mvc.Results.*;

public class ResponseController {

    @Inject
    RecordDAO recordDAO;

    @Transactional
    public Result getAll() {
        List<Record> records = recordDAO.findAll();
        return ok(Json.toJson(records));
    }
}
