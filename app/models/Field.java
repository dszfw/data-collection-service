package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "fields")
public class Field {
    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false)
    @Required
    public String aLabel;

    @ElementCollection
    @CollectionTable(name="field_options", joinColumns=@JoinColumn(name="field_id"))
    public List<String> options;

    @Column(nullable = false)
    @Required
    public boolean required;

    @Column(nullable = false)
    @Required
    public boolean active;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Required
    public FieldType fieldType;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<FieldData> data = new LinkedList<>();

    // Custom validation for options
    public String validate() {
        switch (fieldType) {
            case LINE:
            case TEXTAREA:
            case DATE:
            case CHECK_BOX:
                if (options == null || options.isEmpty()) {
                    return null;
                }
                return "Options should be empty";
            case RADIO_BUTTON:
            case COMBO_BOX:
                if (options == null || options.isEmpty()) {
                    return "Options should be not empty";
                }
                String blanketPattern = "^\\s*$";
                long countBlankets = options.stream()
                        .filter(s -> s.matches(blanketPattern))
                        .count();
                if (countBlankets != 0) {
                    return "Options should not contains blanket values";
                }
                if (options.size() < 2) {
                    return "Options should contains at least two elements";
                }
                return null;
        }
        return "Data is not valid";
    }

}
