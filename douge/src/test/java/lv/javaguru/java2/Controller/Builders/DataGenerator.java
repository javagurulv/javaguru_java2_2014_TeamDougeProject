package lv.javaguru.java2.Controller.Builders;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.MetricDAO;
import lv.javaguru.java2.database.WidgetTypeDAO;
import lv.javaguru.java2.domain.Metric;
import lv.javaguru.java2.domain.WidgetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Sergo on 14.12.2014.
 */
@Component
public class DataGenerator {
    @Autowired
    @Qualifier("ORM_MetricDAO")
    MetricDAO metricDAO;

    @Autowired
    @Qualifier("ORM_WidgetTypeDAO")
    WidgetTypeDAO widgetTypeDAO;

    @Transactional
    public void generateMetrics() throws DBException {
        metricDAO.create( new Metric("Primary","Item Count",30L));
        metricDAO.create( new Metric("Primary","Amount EUR",30L));
        metricDAO.create( new Metric("GroupBy","Staff Name",30L));
        metricDAO.create( new Metric("GroupBy","Film Category",30L));
        metricDAO.create( new Metric("GroupBy","Film Rating",30L));
        metricDAO.create( new Metric("GroupBy","Date",30L));
        metricDAO.create( new Metric("GroupBy","Week",30L));
        metricDAO.create( new Metric("GroupBy","Month",30L));
        metricDAO.create( new Metric("Limit","1",30L));
        metricDAO.create( new Metric("Limit","2",30L));
        metricDAO.create( new Metric("Limit","3",30L));
        metricDAO.create( new Metric("Limit","4",30L));
        metricDAO.create( new Metric("Limit","5",30L));
        metricDAO.create( new Metric("Limit","6",30L));
    }

    @Transactional
    public void generateWidgetTypes() throws DBException {
        widgetTypeDAO.create(new WidgetType("PieChart","PieChart", 2L));
        widgetTypeDAO.create(new WidgetType("BarChart","BarChart", 4L));
        widgetTypeDAO.create(new WidgetType("TimeLine","TimeLine", 8L));
        widgetTypeDAO.create(new WidgetType("Table","Table", 16L));
    }

}
