package lv.javaguru.java2.Controller.View;

import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.value.ValueType;
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

    public String convertToGoogleTable(TableData tableData)
    {
        if(tableData.getTableData().size() == 0){
            return "No Data";
        }
        DataTable dataTable = new DataTable();
        setTableHeader(tableData);
        return null;
    }

}
