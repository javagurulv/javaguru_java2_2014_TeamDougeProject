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
    private Long id;

    @Column(name="primary_id", nullable = false, columnDefinition = "INT(11)")
    private Long primary_id;

    @Column(name="groupby_id", nullable = false, columnDefinition = "INT(11)")
    private Long groupby_id;

    @Column(name="limit_id", nullable = false, columnDefinition = "INT(11)")
    private Long limit_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrimary_id() {
        return primary_id;
    }

    public void setPrimary_id(Long primary_id) {
        this.primary_id = primary_id;
    }

    public Long getGroupby_id() {
        return groupby_id;
    }

    public void setGroupby_id(Long groupby_id) {
        this.groupby_id = groupby_id;
    }

    public Long getLimit_id() {
        return limit_id;
    }

    public void setLimit_id(Long limit_id) {
        this.limit_id = limit_id;
    }
}
