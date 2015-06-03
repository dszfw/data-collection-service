INSERT INTO fields (id, active, aLabel, required, fieldType) VALUES (1, 1, 'First name', 1, 'LINE');
INSERT INTO fields (id, active, aLabel, required, fieldType) VALUES (2, 1, 'Email', 1, 'LINE');
INSERT INTO fields (id, active, aLabel, required, fieldType) VALUES (3, 1, 'Sex', 0, 'RADIO_BUTTON');
INSERT INTO fields (id, active, aLabel, required, fieldType) VALUES (4, 1, 'Date of birth', 0, 'DATE');
INSERT INTO fields (id, active, aLabel, required, fieldType) VALUES (5, 1, 'Sample combobox', 0, 'COMBO_BOX');
INSERT INTO fields (id, active, aLabel, required, fieldType) VALUES (6, 1, 'Cool', 0, 'CHECK_BOX');
INSERT INTO fields (id, active, aLabel, required, fieldType) VALUES (7, 0, 'A line', 1, 'LINE');


INSERT INTO field_options (field_id, options) VALUES (3, "Male");
INSERT INTO field_options (field_id, options) VALUES (3, "Female");
INSERT INTO field_options (field_id, options) VALUES (5, "Option one");
INSERT INTO field_options (field_id, options) VALUES (5, "Option two");
INSERT INTO field_options (field_id, options) VALUES (5, "Option three");


INSERT INTO records (id) VALUES (1);

INSERT INTO field_data (id, field_id, value, record_id) VALUES (1, 1, "Maxim", 1);
INSERT INTO field_data (id, field_id, value, record_id) VALUES (2, 2, "maxim.bobyleu@synesis.ru", 1);
INSERT INTO field_data (id, field_id, value, record_id) VALUES (3, 3, "Male", 1);
INSERT INTO field_data (id, field_id, value, record_id) VALUES (4, 4, "26.07.1990", 1);
INSERT INTO field_data (id, field_id, value, record_id) VALUES (5, 5, "Option one", 1);
INSERT INTO field_data (id, field_id, value, record_id) VALUES (6, 6, "true", 1);
INSERT INTO field_data (id, field_id, value, record_id) VALUES (7, 7, "a line", 1);

INSERT INTO records (id) VALUES (2);

INSERT INTO field_data (id, field_id, value, record_id) VALUES (8, 1, "Vassa", 2);
INSERT INTO field_data (id, field_id, value, record_id) VALUES (9, 2, "vassa@gmail.ru", 2);
INSERT INTO field_data (id, field_id, value, record_id) VALUES (10, 3, "Male", 2);
INSERT INTO field_data (id, field_id, value, record_id) VALUES (11, 4, "21.01.1980", 2);
INSERT INTO field_data (id, field_id, value, record_id) VALUES (12, 5, "Option two", 2);
INSERT INTO field_data (id, field_id, value, record_id) VALUES (13, 6, "false", 2);
INSERT INTO field_data (id, field_id, value, record_id) VALUES (14, 7, "a line", 2);