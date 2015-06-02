package models;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FieldTest {

    @Test
    public void validateOptionsLines() {
        Field field = new Field();

        field.fieldType = FieldType.LINE;
        optionsShouldBeEmpty(field);

        field.fieldType = FieldType.DATE;
        optionsShouldBeEmpty(field);

        field.fieldType = FieldType.TEXTAREA;
        optionsShouldBeEmpty(field);

        field.fieldType = FieldType.RADIO_BUTTON;
        optionsShoulBeMultipleLines(field);
        field.options = "1 line";
        assertThat(field.validate(), notNullValue());
        field.options = "1 line\n2 line\n3line";
        assertThat(field.validate(), nullValue());

        field.fieldType = FieldType.CHECK_BOX;
        optionsShoulBeMultipleLines(field);
        field.options = "1 line";
        assertThat(field.validate(), nullValue());
        field.options = "1 line\n2 line\n3line";
        assertThat(field.validate(), notNullValue());

        field.fieldType = FieldType.COMBO_BOX;
        optionsShoulBeMultipleLines(field);
        field.options = "1 line";
        assertThat(field.validate(), notNullValue());
        field.options = "1 line\n2 line\n3line";
        assertThat(field.validate(), nullValue());
    }

    private void optionsShoulBeMultipleLines(Field field) {
        field.options = "";
        assertThat(field.validate(), notNullValue());
        field.options = "  ";
        assertThat(field.validate(), notNullValue());
        field.options = "\n";
        assertThat(field.validate(), notNullValue());
        field.options = "sfdsfsd\nfdsgfd\n    \n";
        assertThat(field.validate(), notNullValue());
        field.options = "\n\n\n";
        assertThat(field.validate(), notNullValue());
        field.options = "1 line\n2line\n   3 line\n   \n";
        assertThat(field.validate(), notNullValue());
    }

    private void optionsShouldBeEmpty(Field field) {
        field.options = "";
        assertThat(field.validate(), nullValue());
        field.options = "   ";
        assertThat(field.validate(), notNullValue());
        field.options = "some";
        assertThat(field.validate(), notNullValue());
        field.options = "   \n";
        assertThat(field.validate(), notNullValue());
        field.options = "\n";
        assertThat(field.validate(), notNullValue());
        field.options = "\n\n\n";
        assertThat(field.validate(), notNullValue());
    }
}
