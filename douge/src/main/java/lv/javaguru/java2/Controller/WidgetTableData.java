package lv.javaguru.java2.Controller;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.DomainWidgetContent;

import java.util.List;
import java.util.Map;

/**
 * Created by Radchuk on 11/28/2014.
 */
public interface WidgetTableData {
    List<DomainWidgetContent> getWidgetTableData();
    void buildTableData() throws DBException;
    void buildTableData(Map<String, String> params) throws DBException;

    List<DomainWidgetContent> getWidgetTableData(Map<String, String> params) throws DBException;
}
