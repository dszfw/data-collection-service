package controllers;

import dao.FieldDAO;
import dao.RecordDAO;
import models.Field;
import models.FieldData;
import models.Record;
import play.data.Form;
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
    @Inject
    FieldDAO fieldDAO;

    @Transactional
    public Result getAll() {
        List<Record> records = recordDAO.findAll();
        List<Map<String, String>> beJson = new LinkedList<>();
        records.forEach(record -> {
            Map<String, String> map = new HashMap<>();
            record.data.forEach(fieldData -> map.put(fieldData.field.aLabel, fieldData.value));
            beJson.add(map);
        });
        return ok(Json.toJson(beJson));
    }

    @Transactional
    public Result post() {
        Map<String, String> requestData = Form.form()
                .bindFromRequest()
                .data();

        if (requestData.isEmpty()) {
            return badRequest();
        }

        Record record = new Record();

        requestData.forEach((value, fieldId) -> {
            Field field = fieldDAO.find(Long.valueOf(fieldId));
            FieldData fieldData = new FieldData();
            fieldData.field = field;
            fieldData.value = value;
            field.data.add(fieldData);

            record.data.add(fieldData);
            fieldData.record = record;
        });

        recordDAO.save(record);
        return ok();
    }

}
