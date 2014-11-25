package lv.javaguru.java2.Controller.View;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.render.HtmlRenderer;
import com.ibm.icu.util.ULocale;
import lv.javaguru.java2.Controller.TableData;
import org.springframework.stereotype.Component;

/**
 * Created by Juris on 24.11.2014.
 */
@Component("ToGoogleTableDataConverter")
public class ToGoogleTableDataConverter extends TableDataToGoogleWEBTableConverter {


    public DataTable convertDataTableToGoogleTable(TableData tableData) throws TypeMismatchException {
        dataTable = new DataTable();

        setTableHeader(tableData);
        buildTableRows(tableData);


        return dataTable;
    }
}
