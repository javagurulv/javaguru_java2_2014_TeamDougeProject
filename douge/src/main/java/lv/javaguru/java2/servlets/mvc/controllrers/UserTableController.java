package lv.javaguru.java2.servlets.mvc.controllrers;

import com.google.visualization.datasource.base.TypeMismatchException;
import lv.javaguru.java2.Controller.Builders.GoogleVisualizationDataTableBuilder;
import lv.javaguru.java2.Controller.WidgetTableData;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Juris on 10.12.2014.
 */
@Component
public class UserTableController implements MVCController {

    @Autowired @Qualifier("userTableData")
    private WidgetTableData userTableData;

    @Autowired
    GoogleVisualizationDataTableBuilder tableBuilder;

    @Autowired @Qualifier("ORM_UserDAO")
    UserDAO userDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) throws TypeMismatchException {
        try {
            userTableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }

        tableBuilder.prepareInfo(userTableData);

        return new MVCModel("/jsp/users.jsp", tableBuilder.getHTMLDecriptionofOfGoogleVizualizationDataTable());
    }
}
