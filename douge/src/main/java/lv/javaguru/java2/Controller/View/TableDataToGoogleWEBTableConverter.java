package lv.javaguru.java2.Controller.View;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.render.HtmlRenderer;
import com.ibm.icu.util.ULocale;
import lv.javaguru.java2.Controller.TableData;

import java.util.*;

/**
 * Created by Sergo on 15.11.2014.
 */
public class TableDataToGoogleWEBTableConverter {

    private DataTable dataTable;

    private void setTableHeader(TableData tableData){
        ArrayList<ColumnDescription> cd = new ArrayList<ColumnDescription>();
        Set<String> keys = tableData.getTableData().get(0).keySet();
        for (String key : keys){
            cd.add(new ColumnDescription(key,ValueType.TEXT,key));
        }
        dataTable.addColumns(cd);

    }

    private void appendTableRow(Map<String, String> row) throws TypeMismatchException {
        TableRow tableRow = new TableRow();
        Set<String> keys = row.keySet();
        for (String key : keys){
            tableRow.addCell(row.get(key));
        }

        dataTable.addRow(tableRow);
    }

    private void buildTableRows(TableData tableData) throws TypeMismatchException {
        List<Map<String, String>> table = tableData.getTableData();
        for (int i = 0; i < table.size(); i++) {
            appendTableRow(table.get(i));
        }
    }

    public CharSequence convertToGoogleTable(TableData tableData) throws TypeMismatchException {
        if(tableData.getTableData().size() == 0){
            return "No Data";
        }

        setTableHeader(tableData);
        buildTableRows(tableData);

        return HtmlRenderer.renderDataTable(dataTable, ULocale.createCanonical("UTF-8"));

    }
    public TableDataToGoogleWEBTableConverter()
    {
        dataTable = new DataTable();
    }

}
