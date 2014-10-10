package lv.javaguru.java2.domain;

/**
 * Created by Radchuk on 10/10/2014.
 */
public class Widget {
    private Long widget_id;
    private Long dashboard_id;
    private String comments;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDashboard_id(Long dashboard_id) {
        this.dashboard_id = dashboard_id;
    }

    public Long getDashboard_id() {
        return dashboard_id;
    }

    public void setWidget_id(Long widget_id) {
        this.widget_id = widget_id;
    }

    public Long getWidget_id() {
        return widget_id;
    }
}
