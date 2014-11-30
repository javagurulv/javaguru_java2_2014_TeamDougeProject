package lv.javaguru.java2.domain;

import javax.persistence.*;

/**
 * Created by Radchuk on 10/10/2014.
 */
@Entity
@Table(name = "widget_types")
public class WidgetType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false,  columnDefinition = "int(11)")
    private Long id;

    @Column(name = "name", nullable = false,  columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "comments", nullable = false,  columnDefinition = "varchar(255)")
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
