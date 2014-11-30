package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sergo on 21.10.2014.
 */
@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "language_id", nullable = false,  columnDefinition = "tinyint(3)")
    private int language_id;

    @Column(name = "name", nullable = false,  columnDefinition = "char(20)")
    private String name;

    @Column(name = "last_update", nullable = false,  columnDefinition = "timestamp")
    private Date last_update;

    public Language(int language_id, String name, Date last_update) {
        this.language_id = language_id;
        this.name = name;
        this.last_update = last_update;
    }

    public Language(String name, Date last_update) {
        this.name = name;
        this.last_update = last_update;
    }

    public Language() {
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }
}
