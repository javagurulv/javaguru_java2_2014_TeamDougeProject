package lv.javaguru.java2.Controller.Builders;

import com.google.visualization.datasource.base.TypeMismatchException;
import lv.javaguru.java2.Controller.infoClasses.CommonWidgetTableData;
import lv.javaguru.java2.database.ChartDataDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.ChartDataDBDomain;
import lv.javaguru.java2.domain.DomainWidgetContent;
import lv.javaguru.java2.domain.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergo on 14.12.2014.
 */
@Component
public class WidgetDataBuilder {
    @Autowired
    @Qualifier("JDBC_ChartDataDAO")
    private ChartDataDAO chartDataDAO;

    @Autowired
    @Qualifier("WidgetQueryBuilder")
    private WidgetQueryBuilder widgetQueryBuilder;

    @Autowired
    GoogleVisualizationDataTableBuilder tableBuilder;

    public void buildWidgetdata(Widget widget) throws TypeMismatchException, DBException {
        List<Object[]> chartRawDataFromDB = chartDataDAO.getByQueryText(widgetQueryBuilder.buildQuery(widget));
        List<DomainWidgetContent> content = new ArrayList<DomainWidgetContent>();
        if (chartRawDataFromDB.size() > 1){
            Object[] titles = chartRawDataFromDB.get(0);
            for (int i = 1; i < chartRawDataFromDB.size(); i++) {
                Object[] datas = chartRawDataFromDB.get(i);
                ChartDataDBDomain chartData = new ChartDataDBDomain(titles,datas);
                content.add(chartData);
            }
            CommonWidgetTableData widgetTableData = new CommonWidgetTableData(content);
            widget.setJsonWidgetDAta(tableBuilder.getJsonDescriptionOfGoogleVizualizationDataTable(widgetTableData));
        }
    }

}
