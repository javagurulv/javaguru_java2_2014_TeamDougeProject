package lv.javaguru.java2.servlets.mvc.controllrers;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.DashboardDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.WidgetDAO;
import lv.javaguru.java2.domain.Dashboard;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.Widget;
import lv.javaguru.java2.servlets.mvc.models.MVCDashboardModel;
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
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        String sessionLogin = null;
        HttpSession session = request.getSession();

        //check that User sessions is set
        if (session.getAttribute("sessionLogin") != null) {
            sessionLogin = (String) session.getAttribute("sessionLogin");

            //get current user
            User user = null;
            try {
                user = userDAO.getByLogin(sessionLogin);
            } catch (DBException e) {
                e.printStackTrace();
            }

            //get list of dashboard for current user
            try {
                dashboards = dashboardDAO.getAllForUser(user);
            } catch (DBException e) {
                e.printStackTrace();
            }
        }

        Dashboard currentDashboard = null;

        if (request.getParameter("dashboard_id") != null &&
                !request.getParameter("dashboard_id").trim().isEmpty()) {
            //currentDashboardId = Integer.parseInt(request.getParameter("dashboard_id"));
            //Long.parseLong(request.getParameter("dashboard_id"))
            Long currentDashboardId = Long.parseLong(request.getParameter("dashboard_id"));
            try {
                currentDashboard = dashboardDAO.getById(currentDashboardId);
            } catch (DBException e) {
                e.printStackTrace();
            }

            for (Widget widget: currentDashboard.getWidgets()) {
                widget.getWidget_id();
            }
        }

        /*
        //Hibernate workaround: loop through all widgets for current user
        for (Dashboard dashboard: dashboards) {
            dashboard.getWidgets();
            for (Widget widget: dashboard.getWidgets()) {
                widget.getWidget_id();
            }
        }
        */

        MVCDashboardModel mvcDashboardModel;

        mvcDashboardModel = new MVCDashboardModel("/jsp/home.jsp", dashboards, currentDashboard);

        return new MVCModel("/jsp/home.jsp", mvcDashboardModel);
    }
}
