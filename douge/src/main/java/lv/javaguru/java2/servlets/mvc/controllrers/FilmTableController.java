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
 * Created by Juris on 09.11.2014.
 */
@Component
public class FilmTableController implements MVCController {
    @Autowired @Qualifier("filmTableData")
    private WidgetTableData filmTableData;
    @Autowired
    GoogleVisualizationDataTableBuilder tableBuilder;
    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        try {
            filmTableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }
        try {
            tableBuilder.prepareInfo(filmTableData);
        } catch (TypeMismatchException e) {
            e.printStackTrace();
        }

        return  new MVCModel("/jsp/films.jsp", tableBuilder.getHTMLDecriptionofOfGoogleVizualizationDataTable());

    }
}
