package lv.javaguru.java2.domain;

/**
 * Created by Radchuk on 10/10/2014.
 */
public class WidgetType {
    private Long id;
    private String name;
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
