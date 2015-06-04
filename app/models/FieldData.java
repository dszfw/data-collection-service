package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static models.FieldType.COMBO_BOX;
import static models.FieldType.RADIO_BUTTON;
import static play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "field_data")
public class FieldData {
    @Id
    @GeneratedValue
    public Long id;

    public String value;

    @ManyToOne(optional = false)
    @Required
    public Field field;

    @ManyToOne/*(optional = false)*/
    @JsonIgnore
    @Required
    public Record record;

    public String validate() {
        if (field.required && (value == null || value.trim().isEmpty())) {
            return "Value property is required!";
        }
        FieldType fieldType = field.fieldType;
        if (fieldType == COMBO_BOX || fieldType == RADIO_BUTTON) {
            if (!field.required && value.isEmpty()) {
                return null;
            } else if (!field.options.contains(value)) {
                return "Value property should be definite from options for combobox or radio button";
            }
        }
        return null;
    }
}
