package lv.javaguru.java2.domain;

/**
 * Created by Radchuk on 10/10/2014.
 */
public class Widget {
    private Long widget_id;
    private Long dashboard_id;
    private String comments;
    private long metric_set_id;

    public long getMetric_set_id() {
        return metric_set_id;
    }

    public void setMetric_set_id(long metric_set_id) {
        this.metric_set_id = metric_set_id;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getWidget_type_id() {
        return widget_type_id;
    }

    public void setWidget_type_id(long widget_type_id) {
        this.widget_type_id = widget_type_id;
    }

    private long position;
    private long widget_type_id;

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
