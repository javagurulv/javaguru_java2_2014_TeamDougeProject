package lv.javaguru.java2.servlets.mvc.controllrers;

import com.google.visualization.datasource.base.TypeMismatchException;
import lv.javaguru.java2.Controller.Builders.GoogleVisualizationDataTableBuilder;


import lv.javaguru.java2.Controller.WidgetTableData;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by Sergo on 24.11.2014.
 */
@Component
public class TestController implements MVCController {

   // @Autowired @Qualifier("chartInfoBuilderRentalCount")
   // private WidgetTableData tableData;

   // @Autowired
   // private GoogleVisualizationDataTableBuilder tableBuilder;

    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) throws TypeMismatchException {
       /* try {
            tableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }

        tableBuilder.prepareInfo(tableData);
        return new MVCModel("/jsp/test.jsp", tableBuilder.getJsonDescriptionOfGoogleVizualizationDataTable());*/

        return new MVCModel("/jsp/test.jsp", null);

    }
}
