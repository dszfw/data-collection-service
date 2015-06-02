package models;

import javax.persistence.*;

@Entity
@Table(name = "data")
public class Data {

    @Id
    @GeneratedValue
    public Long id;

    public String value;

    @ManyToOne
    public Field field;
}
