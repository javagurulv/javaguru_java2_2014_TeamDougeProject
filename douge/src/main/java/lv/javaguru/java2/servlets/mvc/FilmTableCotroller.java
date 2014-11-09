package lv.javaguru.java2.servlets.mvc;

import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.TableDataFactory;
import lv.javaguru.java2.Controller.View.TableDataToWEBTableConverter;
import lv.javaguru.java2.database.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Juris on 09.11.2014.
 */
public class FilmTableCotroller implements MVCController {

    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {
        TableData filmTableData = TableDataFactory.getInstance().getFilmTableData();
        try {
            filmTableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }
        TableDataToWEBTableConverter tableDataToWEBTableConverter = new TableDataToWEBTableConverter();
        return new MVCModel("/films.jsp",tableDataToWEBTableConverter.convertToWebTable(filmTableData));

    }
}
