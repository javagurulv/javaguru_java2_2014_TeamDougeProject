package lv.javaguru.java2.servlets.mvc.controllrers;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.DashboardDAO;
import lv.javaguru.java2.database.UserDAO;
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

/**
 * Created by user on 12-Dec-14.
 */
@Component
public class AddDashboardControllerImpl implements AddDashboardController {

    @Autowired
    @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("ORM_DashboardDAO")
    private DashboardDAO dashboardDAO;

    @Override
    @Transactional
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        Integer errorCode = 0;

        HttpSession session = request.getSession();
        //check that User sessions is set
        if (session.getAttribute("sessionLogin") != null) {
            String sessionLogin = (String) session.getAttribute("sessionLogin");

            //get current user
            User user = null;
            try {
                user = userDAO.getByLogin(sessionLogin);
            } catch (DBException e) {
                e.printStackTrace();
            }

            if (request.getParameter("submit") != null) {
                if (request.getParameter("dashboard_name") != null &&
                        !request.getParameter("dashboard_name").trim().isEmpty()) {
                    Dashboard dashboard = createDashboardFromRequest(request, user);
                    storeDashboardToDatabase(dashboard);
                } else {
                    errorCode = 1; //Dashboard name is empty
                }
            }
        } else {
            errorCode = 2; //User not logged in
        }

        return new MVCModel("/jsp/adddashboard.jsp", errorCode);
    }

    private void storeDashboardToDatabase(Dashboard dashboard) {
        try {
            dashboardDAO.create(dashboard);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    protected Dashboard createDashboardFromRequest(HttpServletRequest request, User user) {
        Dashboard dashboard = new Dashboard();
        dashboard.setName(request.getParameter("dashboard_name"));
        dashboard.setUser(user);
        return dashboard;
    }
}
