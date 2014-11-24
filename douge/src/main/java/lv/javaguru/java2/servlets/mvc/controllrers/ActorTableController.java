package lv.javaguru.java2.servlets.mvc.controllrers;
import com.google.visualization.datasource.base.TypeMismatchException;
import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.TableDataFactory;
import lv.javaguru.java2.Controller.View.TableDataToGoogleWEBTableConverter;
import lv.javaguru.java2.Controller.View.TableDataToWEBTableConverter;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Juris on 08.11.2014.
 */
@Component
public class ActorTableController implements MVCController {

    @Autowired @Qualifier("actorTableData")
    private TableData actorTableData;
    @Autowired @Qualifier("tableDataToGoogleWEBTableConverter")
    TableDataToGoogleWEBTableConverter tableDataToWEBTableConverter;

    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        try {
            actorTableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }

        try {
            return new MVCModel("/jsp/actors.jsp", tableDataToWEBTableConverter.convertToGoogleTable(actorTableData));
        } catch (TypeMismatchException e) {
            e.printStackTrace();
        }
        return null;
    }
}
