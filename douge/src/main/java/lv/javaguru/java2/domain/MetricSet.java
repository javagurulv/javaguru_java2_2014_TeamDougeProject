package lv.javaguru.java2.domain;

import javax.persistence.*;

/**
 * Created by Radchuk on 11/10/2014.
 */
@Entity
@Table(name="metrics_sets")
public class MetricSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="record_id", nullable = false, columnDefinition = "INT(11)")
    private Integer record_id;

    @Column(name="id", nullable = false, columnDefinition = "INT(11)")
    private Integer id;

    @Column(name="metric_id", nullable = false, columnDefinition = "INT(11)")
    private Integer metric_id;

    public Integer getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.id = record_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMetric_id() {
        return metric_id;
    }

    public void setMetric_id(Integer metric_id) {
        this.metric_id = metric_id;
    }
}
