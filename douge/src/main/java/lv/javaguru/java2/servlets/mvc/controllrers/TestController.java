package lv.javaguru.java2.servlets.mvc.controllrers;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;


import com.google.visualization.datasource.render.JsonRenderer;
import com.ibm.icu.util.ULocale;
import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.View.ToGoogleTableDataConverter;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static com.google.visualization.datasource.render.JsonRenderer.renderDataTable;

/**
 * Created by Sergo on 24.11.2014.
 */
@Component
public class TestController implements MVCController {

    @Autowired @Qualifier("chartInfoBuilderRentalCount")
    private TableData tableData;

    @Autowired @Qualifier("ToGoogleTableDataConverter")
    private ToGoogleTableDataConverter toGoogleTableDataConverter;

    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) throws TypeMismatchException {
        try {
            tableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }

        DataTable dt = toGoogleTableDataConverter.convertDataTableToGoogleTable(tableData);
        //CharSequence str = CsvRenderer.renderDataTable(dt, ULocale.createCanonical("UTF-8"),",");
        CharSequence str1 = JsonRenderer.renderDataTable(dt, true, true, true);
        System.out.println(str1);
        return new MVCModel("/jsp/test.jsp", str1);
       // return null;

    }
}
