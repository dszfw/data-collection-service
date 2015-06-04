package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import dao.FieldDAO;
import dao.RecordDAO;
import models.Field;
import models.FieldData;
import models.Record;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.WebSocket;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static play.mvc.Results.*;

public class ResponseController {

    @Inject
    RecordDAO recordDAO;
    @Inject
    FieldDAO fieldDAO;

    private Map<String, WebSocket.Out<JsonNode>> clients = new ConcurrentHashMap<>();

    public  WebSocket<JsonNode> wsResponses() {
        String uuid = UUID.randomUUID().toString();
        return WebSocket.whenReady((in, out) -> {
            clients.put(uuid, out);
            in.onClose(() -> clients.remove(uuid));
        });
    }

    @Transactional
    public Result getAll() {
        // TODO find all active
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

        requestData.forEach((f, value) -> {
            Long fieldId = Long.valueOf(f.replaceFirst("field_", ""));
            Field field = fieldDAO.find(fieldId);
            FieldData fieldData = new FieldData();
            fieldData.field = field;
            fieldData.value = value;
            // TODO FIX IT
            String isValid = fieldData.validate();
            if (isValid != null) {
                throw new ConstraintViolationException(isValid, null);
            }
            field.data.add(fieldData);

            record.data.add(fieldData);
            fieldData.record = record;
        });

        // TODO check for ConstraintViolationException
        recordDAO.save(record);

        if (!clients.isEmpty()) {
            clients.forEach((uuid, out) -> {
                // TODO doube usage
                Map<String, String> map = new HashMap<>();
                record.data.forEach(fieldData -> map.put(fieldData.field.aLabel, fieldData.value));
                out.write(Json.toJson(map));
            });
        }
        return ok();
    }

}
