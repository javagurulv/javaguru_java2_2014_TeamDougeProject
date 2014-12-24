package lv.javaguru.java2.Controller.infoClasses;

import lv.javaguru.java2.Controller.WidgetTableData;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.DomainWidgetContent;

import java.util.List;
import java.util.Map;

/**
 * Created by Sergo on 14.12.2014.
 */
public class CommonWidgetTableData implements WidgetTableData {

    private List<DomainWidgetContent> contents;
    @Override
    public List<DomainWidgetContent> getWidgetTableData() {
        return contents;
    }

    @Override
    public void buildTableData() throws DBException {

    }

    @Override
    public void buildTableData(Map<String, String> params) throws DBException {

    }
    public  CommonWidgetTableData(List<DomainWidgetContent> contents){
        this.contents = contents;
    }

    @Override
    public List<DomainWidgetContent> getWidgetTableData(Map<String, String> params) throws DBException {
        return null;
    }
}
