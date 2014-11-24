package lv.javaguru.java2.Controller.Builders;

import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.database.AmountRentalCountDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.AmountRentalCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Radchuk on 11/24/2014.
 */
@Component("chartInfoBuilderRentalCount")
public class ChartInfoBuilder implements TableData {
    @Autowired
    AmountRentalCountDAO amountRentalCountDAO;

    private ArrayList<Map<String,String>> tableData;

    protected void buildInfo() throws DBException {
        tableData = new ArrayList<Map<String, String>>();

        List<AmountRentalCount> amountRentalCounts = amountRentalCountDAO.getAmountRentalCountData();
        for (int i = 0; i < amountRentalCounts.size() ; i++) {

            tableData.add(amountRentalCounts.get(i).getInfoMap());
        }
    }

    @Override
    public ArrayList<Map<String, String>> getTableData() {
        return tableData;
    }

    @Override
    public void buildTableData() throws DBException {
        buildInfo();
    }
}
