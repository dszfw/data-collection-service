package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import static play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "field_data")
public class FieldData {
    @Id
    @GeneratedValue
    public Long id;

    @Required
    public String value;

    @ManyToOne(optional = false)
    @Required
    public Field field;

    @ManyToOne/*(optional = false)*/
    @JsonIgnore
    @Required
    public Record record;
}
