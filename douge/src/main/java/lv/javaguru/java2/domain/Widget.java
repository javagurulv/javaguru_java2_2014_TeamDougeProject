package lv.javaguru.java2.domain;

import javax.persistence.*;

/**
 * Created by Radchuk on 10/10/2014.
 */

@Entity
@Table(name="widgets")
public class Widget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", columnDefinition = "INT(11)")
    private Long widget_id;

    //@Column(name="dashboard_id", nullable = false, columnDefinition = "INT(11)")
    @Transient
    private Long dashboard_id;

    @Column(name="comments", nullable = false)
    private String comments;

    @Column(name="metric_set_id", nullable = false, columnDefinition = "INT(11)")
    private long metric_set_id;

    @Column(name="position", nullable = false, columnDefinition = "INT(11)")
    private long position;

    @Column(name="type_id", nullable = false, columnDefinition = "INT(11)")
    private long widget_type_id;

    @ManyToOne(fetch = FetchType.LAZY)
    //@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dashboard_id", nullable = false)
    private Dashboard dashboard;

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


    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
}
