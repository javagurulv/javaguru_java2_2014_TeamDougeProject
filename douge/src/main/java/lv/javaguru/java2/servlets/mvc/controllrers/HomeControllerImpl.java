package lv.javaguru.java2.servlets.mvc.controllrers;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.DashboardDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.WidgetDAO;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 05-Dec-14.
 */
@Component
public class HomeControllerImpl implements HomeController {

    List<Dashboard> dashboards = new ArrayList<Dashboard>();

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("ORM_DashboardDAO")
    private DashboardDAO dashboardDAO;

    @Autowired
    @Qualifier("ORM_WidgetDAO")
    private WidgetDAO widgetDAO;

    @Override
    @Transactional
    public MVCModel processRequest(HttpServletRequest req, HttpServletResponse resp) {

        String sessionLogin = null;

        HttpSession session = req.getSession();

        if (session.getAttribute("sessionLogin") != null) {

            sessionLogin = (String) session.getAttribute("sessionLogin");

            User user = null;
            try {
                user = userDAO.getByLogin(sessionLogin);
            } catch (DBException e) {
                e.printStackTrace();
            }

            try {
                dashboards = dashboardDAO.getAllForUser(user);
            } catch (DBException e) {
                e.printStackTrace();
            }
        }

        for (Dashboard dashboard: dashboards) {
            dashboard.getWidgets();
        }



        return new MVCModel("/jsp/home.jsp", dashboards);
    }
}
