package lv.javaguru.java2.Controller.Builders;

import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.WidgetTableData;
import lv.javaguru.java2.database.AmountRentalCountDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.AmountRentalCount;
import lv.javaguru.java2.domain.DomainWidgetContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Radchuk on 11/24/2014.
 */
@Component("chartInfoBuilderRentalCount")
public class ChartInfoBuilder implements WidgetTableData {
    @Autowired
    AmountRentalCountDAO amountRentalCountDAO;

    private List<DomainWidgetContent> tableData;

    protected void buildInfo() throws DBException {
        tableData = amountRentalCountDAO.getAmountRentalCountData();

    }

    @Override
    public List<DomainWidgetContent> getWidgetTableData() {
        return tableData;
    }

    @Override
    public void buildTableData() throws DBException {
        buildInfo();
    }

    @Override
    public void buildTableData(Map<String, String> params) throws DBException {
        buildTableData();
    }
}
