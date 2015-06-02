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

INSERT INTO data (id, field_id, value) VALUES (1, 1, "Maxim");
INSERT INTO data (id, field_id, value) VALUES (2, 2, "maxim.bobyleu@synesis.ru");
INSERT INTO data (id, field_id, value) VALUES (3, 2, "maxim.bobyleu@synesis.ru");
INSERT INTO data (id, field_id, value) VALUES (4, 2, "maxim.bobyleu@synesis.ru");
INSERT INTO data (id, field_id, value) VALUES (5, 2, "maxim.bobyleu@synesis.ru");