package lv.javaguru.java2.domain;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Viktor on 01/07/2014.
 */
@Entity
@Table(name="users")
public class User extends DBDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition = "int(11)")
    protected Long userId;

    @Column(name="login", nullable = false, columnDefinition = "varchar(45)")
    protected String login;

    @Column(name="user_type", nullable = false, columnDefinition = "int(11)")
    protected long user_type;

    @Column(name="passwd", nullable = false, columnDefinition = "varchar(45)")
    protected String password;

    @Column(name="comments", nullable = true, columnDefinition = "varchar(255)")
    protected String comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Dashboard> dashboards;

    public long getUser_type() {
        return user_type;
    }

    public void setUser_type(long user_type) {
        this.user_type = user_type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public List<Dashboard> getDashboards() {
        return dashboards;
    }
}
