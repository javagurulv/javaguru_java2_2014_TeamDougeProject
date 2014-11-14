package lv.javaguru.java2.Controller.View;


import lv.javaguru.java2.Controller.TableData;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sergo on 04.11.2014.
 */
public class TableDataToWEBTableConverter {

    private String createTableHeader(TableData tableData)
    {
        String tableHeader = "<table>";
        Set<String> keys = tableData.getTableData().get(0).keySet();
        tableHeader += "<tr>";
        for (String key : keys){
            tableHeader +=  "<th>" + key + "</th>";
        }
        tableHeader += "</tr>";
        return tableHeader;
    }
    private String createTableFooter()
    {
        return "</table>";
    }

    private String createTableRow(TableData tableData, int rowNumber)
    {
        String tableRow = "<tr>";
        Set<String> keys = tableData.getTableData().get(0).keySet();
        for (String key : keys){
            tableRow += "<td>" + tableData.getTableData().get(rowNumber).get(key) + "</td>";
        }

        tableRow += "<tr>";
        return tableRow;
    }

    private String createTableBody(TableData tableData)
    {
        String tableBody = "";
        for (int i = 0; i < tableData.getTableData().size(); i++) {
            tableBody += createTableRow(tableData, i);
        }
        return tableBody;
    }

    public String convertToWebTable(TableData tableData)
    {
        String resultTable = null;
        if (tableData.getTableData().size() > 0) {
            resultTable  = createTableHeader(tableData);
            resultTable += createTableBody(tableData);
            resultTable += createTableFooter();
        }
        else{
            resultTable = "No Data";
        }
        return resultTable;
    }

}
