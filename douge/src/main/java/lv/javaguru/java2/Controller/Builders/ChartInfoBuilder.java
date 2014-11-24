package lv.javaguru.java2.Controller.Builders;

import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.database.AmountRentalCountDAO;
import lv.javaguru.java2.database.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Radchuk on 11/24/2014.
 */
@Component("chartInfoBuilderRentalCount")
public class ChartInfoBuilder implements TableData {
    @Autowired
    AmountRentalCountDAO amountRentalCountDAO;



    @Override
    public ArrayList<Map<String, String>> getTableData() {
        return null;
    }

    @Override
    public void buildTableData() throws DBException {

    }
}
