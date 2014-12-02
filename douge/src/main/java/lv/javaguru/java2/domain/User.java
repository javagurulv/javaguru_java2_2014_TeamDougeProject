package lv.javaguru.java2.domain;

import javax.persistence.*;

/**
 * Created by Viktor on 01/07/2014.
 */
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition = "int(11)")
    private long userId;

    @Column(name="login", nullable = false, columnDefinition = "varchar(45)")
    private String login;

    @Column(name="user_type", nullable = false, columnDefinition = "int(11)")
    private long user_type;

    @Column(name="passwd", nullable = false, columnDefinition = "varchar(45)")
    private String password;

    @Column(name="comments", nullable = true, columnDefinition = "varchar(255)")
    private String comments;

    public long getUser_type() {
        return user_type;
    }

    public void setUser_type(long user_type) {
        this.user_type = user_type;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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
}
