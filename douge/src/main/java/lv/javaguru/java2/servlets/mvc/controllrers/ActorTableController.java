package lv.javaguru.java2.servlets.mvc.controllrers;
import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.TableDataFactory;
import lv.javaguru.java2.Controller.View.TableDataToWEBTableConverter;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Juris on 08.11.2014.
 */
@Component
public class ActorTableController implements MVCController {
    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        TableData actorTableData = TableDataFactory.getInstance().getActorTableData();
        try {
            actorTableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }
        TableDataToWEBTableConverter tableDataToWEBTableConverter = new TableDataToWEBTableConverter();

        return new MVCModel("/jsp/actors.jsp", tableDataToWEBTableConverter.convertToWebTable(actorTableData));
    }
}
