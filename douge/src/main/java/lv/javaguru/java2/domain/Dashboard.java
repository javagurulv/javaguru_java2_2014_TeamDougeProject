package lv.javaguru.java2.domain;

/**
 * Created by Radchuk on 10/10/2014.
 */
public class Dashboard {

    private Long id;
    private Long user_id;
    private String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
}
