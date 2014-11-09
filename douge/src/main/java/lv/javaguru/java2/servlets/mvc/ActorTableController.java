package lv.javaguru.java2.servlets.mvc;
import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.TableDataFactory;
import lv.javaguru.java2.Controller.View.TableDataToWEBTableConverter;
import lv.javaguru.java2.database.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Juris on 08.11.2014.
 */
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

        return new MVCModel("/actors.jsp",tableDataToWEBTableConverter.convertToWebTable(actorTableData));
    }
}