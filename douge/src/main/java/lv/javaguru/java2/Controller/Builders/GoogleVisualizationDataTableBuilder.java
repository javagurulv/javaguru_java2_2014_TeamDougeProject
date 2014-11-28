package lv.javaguru.java2.Controller.Builders;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.ValueFormatter;

import com.google.visualization.datasource.render.HtmlRenderer;
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

    private DataTable dataTable  = null;

    private void setColumns(WidgetTableData tableData){

        Map<String, DBDomainDataInfo> infoMap = tableData.getWidgetTableData().get(0).getFullDomainInfo();
        for (String key : infoMap.keySet())
        {
            DBDomainDataInfo info =infoMap.get(key);
            dataTable.addColumn(new ColumnDescription(key,info.getDataType(),key));
        }
        // dataTable.addColumns(cd);
    }

    private void addRow(WidgetTableData tableData, Integer position) throws TypeMismatchException {
        Map<String, DBDomainDataInfo> infoMap = tableData.getWidgetTableData().get(position).getFullDomainInfo();

        TableRow tableRow = new TableRow();
        Set<String> keys = infoMap.keySet();
        for (String key : keys){
            ValueFormatter valueFormatter = ValueFormatter.createDefault(infoMap.get(key).getDataType(), ULocale.createCanonical("UTF-8"));
            tableRow.addCell(valueFormatter.parse(infoMap.get(key).getDataValue()));
        }
        dataTable.addRow(tableRow);


    }

    private void setRows(WidgetTableData tableData) throws TypeMismatchException {
        for (int i = 0; i < tableData.getWidgetTableData().size() ; i++) {
            addRow(tableData, i);
        }

    }

    public void prepareInfo(WidgetTableData tableData) throws TypeMismatchException {
        dataTable = new DataTable();
        List<DomainWidgetContent> widgetContents = tableData.getWidgetTableData();
        if (widgetContents.size() > 0){
            setColumns(tableData);
            setRows(tableData);
        }
    }

    public DataTable getGoogleVizualizationDataTable(){
        return dataTable;
    }

    public String getJsonDescriptionOfGoogleVizualizationDataTable(){
        return JsonRenderer.renderDataTable(dataTable, true, true, true).toString();
    }

    public String getHTMLDecriptionofOfGoogleVizualizationDataTable(){
        return HtmlRenderer.renderDataTable(dataTable, ULocale.createCanonical("UTF-8")).toString();
    }
}
