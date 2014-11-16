package lv.javaguru.java2.servlets.mvc.controllrers;

import com.google.visualization.datasource.base.TypeMismatchException;
import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.TableDataFactory;
import lv.javaguru.java2.Controller.View.TableDataToGoogleWEBTableConverter;
import lv.javaguru.java2.Controller.View.TableDataToWEBTableConverter;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Juris on 09.11.2014.
 */
@Component
public class FilmTableController implements MVCController {

    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {
        TableData filmTableData = TableDataFactory.getInstance().getFilmTableData();
        try {
            filmTableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }
        //TableDataToWEBTableConverter tableDataToWEBTableConverter = new TableDataToWEBTableConverter();
       // return new MVCModel("/jsp/films.jsp", tableDataToWEBTableConverter.convertToWebTable(filmTableData));
        try {
            return  new MVCModel("/jsp/films.jsp", new TableDataToGoogleWEBTableConverter().convertToGoogleTable(filmTableData));
        } catch (TypeMismatchException e) {
            e.printStackTrace();
        }
        return null;
    }
}
