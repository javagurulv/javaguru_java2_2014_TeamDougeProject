package lv.javaguru.java2.Controller;

import lv.javaguru.java2.database.DBException;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Sergo on 02.11.2014.
 */
public interface TableData {

    // return data as Map<String, String> , because it's comfortable for table making;
    ArrayList<Map<String, String>> getTableData();
    void buildTableData() throws DBException;

}
