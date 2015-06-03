package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue
    public Long id;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL)
    public List<FieldData> data;
}
