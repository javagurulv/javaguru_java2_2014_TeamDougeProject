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
    private Long id;

    @Column(name="type", nullable = false, columnDefinition = "varchar(50)")
    private  String type;

    @Column(name="compatibility", nullable = false, columnDefinition = "int(11)")
    private Long compatibility;

    @Column(name="name", nullable = false, columnDefinition = "varchar(255)")
    private String name;

    public Metric(){}

    public Metric(String type,String name, Long compatibility){
        this.name = name;
        this.type = type;
        this.compatibility = compatibility;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(Long compatibility) {
        this.compatibility = compatibility;
    }
}
