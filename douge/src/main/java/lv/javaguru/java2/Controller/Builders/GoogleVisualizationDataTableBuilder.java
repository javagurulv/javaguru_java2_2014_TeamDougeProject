package lv.javaguru.java2.Controller.Builders;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.ValueFormatter;


import com.google.visualization.datasource.render.JsonRenderer;
import com.ibm.icu.util.ULocale;
import lv.javaguru.java2.Controller.WidgetTableData;
import lv.javaguru.java2.domain.DomainWidgetContent;
import lv.javaguru.java2.domain.DBDomainDataInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sergo on 25.11.2014.
 */
@Component
public class GoogleVisualizationDataTableBuilder {



   //------------------------------------------------- Thread safe implementation ------------------------------------//
    private void addRow(WidgetTableData tableData, DataTable dataTable, Integer position) throws TypeMismatchException {
        Map<String, DBDomainDataInfo> infoMap = tableData.getWidgetTableData().get(position).getFullDomainInfo();

        TableRow tableRow = new TableRow();
        Set<String> keys = infoMap.keySet();
        for (String key : keys){
            ValueFormatter valueFormatter = ValueFormatter.createDefault(infoMap.get(key).getDataType(), ULocale.createCanonical("UTF-8"));
            tableRow.addCell(valueFormatter.parse(infoMap.get(key).getDataValue()));
        }
        dataTable.addRow(tableRow);


    }

    private void setRows(WidgetTableData tableData, DataTable dataTable) throws TypeMismatchException {
        for (int i = 0; i < tableData.getWidgetTableData().size() ; i++) {
            addRow(tableData,dataTable,i);
        }
    }


    private void setColumns(WidgetTableData tableData , DataTable dataTable){

        Map<String, DBDomainDataInfo> infoMap = tableData.getWidgetTableData().get(0).getFullDomainInfo();
        for (String key : infoMap.keySet())
        {
            DBDomainDataInfo info =infoMap.get(key);
            dataTable.addColumn(new ColumnDescription(key,info.getDataType(),key));
        }
        // dataTable.addColumns(cd);
    }

    protected void buildDataSet(WidgetTableData tableData, DataTable dataTable) throws TypeMismatchException {
        List<DomainWidgetContent> widgetContents = tableData.getWidgetTableData();
        if (widgetContents.size() > 0){
            setColumns(tableData,dataTable);
            setRows(tableData,dataTable);
        }
    }

    //------------------------------------------- End of thread safe implementation --------------------------//




    public String  getJsonDescriptionOfGoogleVizualizationDataTable(WidgetTableData tableData) throws TypeMismatchException {
        DataTable dataSet = new DataTable();
        buildDataSet(tableData, dataSet);
        String result = JsonRenderer.renderDataTable(dataSet, true,true,true).toString();
        result = result.replace("\"", "\\\"");
        return result;
    }

}
