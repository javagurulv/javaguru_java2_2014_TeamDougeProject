package lv.javaguru.java2.domain;

/**
 * Created by Viktor on 01/07/2014.
 */
public class User {

    private long userId;
    private String login;
    private String password;
    private String comments;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLoginLogin(String login) {
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
