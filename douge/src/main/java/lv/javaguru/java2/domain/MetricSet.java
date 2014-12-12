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
    @Column(name="id", nullable = false, columnDefinition = "INT(11)")
    private Integer id;

    @Column(name="primary_id", nullable = false, columnDefinition = "INT(11)")
    private Integer primary_id;

    @Column(name="groupby_id", nullable = false, columnDefinition = "INT(11)")
    private Integer groupby_id;

    @Column(name="limit_id", nullable = false, columnDefinition = "INT(11)")
    private Integer limit_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrimary_id() {
        return primary_id;
    }

    public void setPrimary_id(Integer primary_id) {
        this.primary_id = primary_id;
    }

    public Integer getGroupby_id() {
        return groupby_id;
    }

    public void setGroupby_id(Integer groupby_id) {
        this.groupby_id = groupby_id;
    }

    public Integer getLimit_id() {
        return limit_id;
    }

    public void setLimit_id(Integer limit_id) {
        this.limit_id = limit_id;
    }
}
