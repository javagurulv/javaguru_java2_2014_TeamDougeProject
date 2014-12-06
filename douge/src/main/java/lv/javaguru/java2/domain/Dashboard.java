package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Radchuk on 10/10/2014.
 */
@Entity
@Table(name="dashboards")
public class Dashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition = "int(11)")
    private Long id;

    //@Column(name="user_id", nullable = false, columnDefinition = "int(11)")
    @Transient
    private Long user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    //@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="name", nullable = true, columnDefinition = "varchar(255)")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dashboard")
    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "dashboard")
    private List<Widget> widgets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public User getUser() {
        return user;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
