package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "field_data")
public class FieldData {
    @Id
    @GeneratedValue
    public Long id;

    public String value;

    @ManyToOne(optional = false)
    public Field field;

    @ManyToOne/*(optional = false)*/
    @JsonIgnore
    public Record record;
}
