package controllers;

import dao.RecordDAO;
import models.Record;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static play.mvc.Results.*;

public class ResponseController {

    @Inject
    RecordDAO recordDAO;

    @Transactional
    public Result getAll() {
        List<Record> records = recordDAO.findAll();
        List<Map<String, String>> beJson = new LinkedList<>();
        records.forEach(record -> {
            Map<String, String> map = new HashMap<String, String>();
            record.data.forEach(fieldData -> {
                map.put(fieldData.field.aLabel, fieldData.value);
            });
            beJson.add(map);
        });
        return ok(Json.toJson(beJson));
    }
}
