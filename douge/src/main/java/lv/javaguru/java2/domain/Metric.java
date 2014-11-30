package lv.javaguru.java2.domain;

import javax.persistence.*;

/**
 * Created by Radchuk on 11/10/2014.
 */
@Entity
@Table(name="metrics")
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition = "int(11)")
    private Integer id;

    @Column(name="type", nullable = false, columnDefinition = "varchar(50)")
    private  String type;

    @Column(name="compatibility", nullable = false, columnDefinition = "int(11)")
    private Integer compatibility;

    @Column(name="name", nullable = false, columnDefinition = "varchar(255)")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(Integer compatibility) {
        this.compatibility = compatibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
