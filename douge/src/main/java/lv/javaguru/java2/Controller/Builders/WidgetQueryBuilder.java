package lv.javaguru.java2.Controller.Builders;


import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.MetricDAO;
import lv.javaguru.java2.database.MetricSetDAO;
import lv.javaguru.java2.domain.Metric;
import lv.javaguru.java2.domain.MetricSet;
import lv.javaguru.java2.domain.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Sergo on 11.12.2014.
 */
@Component("WidgetQueryBuilder")
public class WidgetQueryBuilder {

    private final String countField = "rental_id";
    private final String amountField = "amount";
    private final String queryTemplate = "SELECT {SECONDARY},{PRIMARY} FROM DATA_UNITED GROUP BY {SECONDARY} ORDER BY {PRIMARY} DESC LIMIT {LIMIT}";
    private final String primarySign  = "{PRIMARY}";
    private final String secondarySign = "{SECONDARY}";
    private final String limitSign = "{LIMIT}";

    @Autowired
    @Qualifier("JDBC_MetricDAO")
    private MetricDAO metricDAO;

    @Autowired
    @Qualifier("ORM_MetricSetDAO")
    private MetricSetDAO metricSetDAO;

    public String buidQuery(String primaryMetric,String secondaryMetric, Long limit)
    {

        String result =  queryTemplate;
        result = result.replace("{SECONDARY}", secondaryMetric);
        result = result.replace("{PRIMARY}", primaryMetric);
        result = result.replace("{LIMIT}", limit.toString());

        return result;
    }

    public String buildQuery(Long primaryMetricID, Long secondaryMetricID, Long limit) throws DBException
    {

        Metric primaryMetric = metricDAO.getById(primaryMetricID);
        Metric secondaryMetric = metricDAO.getById(secondaryMetricID);
        String primaryStr = "";
        if (primaryMetric.getName().toUpperCase().equals("COUNT")){
            primaryStr ="count("+ countField + ")";
        }

        else if (primaryMetric.getName().toUpperCase().equals("AMOUNT")){
            primaryStr = "sum(" + amountField + ")";
        }

        String secondStr = secondaryMetric.getName();
        return  buidQuery(primaryStr,secondStr,limit);
    }

    public String buildQuery(Widget widget) throws DBException {
        MetricSet metricSet = metricSetDAO.getById(widget.getMetric_set_id());
        Long m_id = metricSet.getPrimary_id();
        Metric metricPrimary = metricDAO.getById(metricSet.getPrimary_id());
        Metric metricGroupBy = metricDAO.getById(metricSet.getGroupby_id());
        Metric metricLimit = metricDAO.getById(metricSet.getLimit_id());
        return  null;
    }
}
