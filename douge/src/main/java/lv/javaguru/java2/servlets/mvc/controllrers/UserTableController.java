package lv.javaguru.java2.servlets.mvc.controllrers;

import com.google.visualization.datasource.base.TypeMismatchException;
import lv.javaguru.java2.Controller.Builders.GoogleVisualizationDataTableBuilder;
import lv.javaguru.java2.Controller.WidgetTableData;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Juris on 10.12.2014.
 */
@Controller
public class UserTableController{

    @Autowired @Qualifier("userTableData")
    private WidgetTableData userTableData;

    @Autowired
    GoogleVisualizationDataTableBuilder tableBuilder;

    @Autowired @Qualifier("ORM_UserDAO")
    UserDAO userDAO;

    @RequestMapping(value = "users", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response) throws TypeMismatchException {
        System.out.println(request.getParameterMap().toString());
        HttpSession session = request.getSession();
        try {
            Long currentUserType = ((User) session.getAttribute("user")).getUser_type();
            if ((request.getParameter("useraction") != null))//
                if ((currentUserType == 0)) {

                    doUserAction(request);
                }
        }
        catch (Exception e){}
        finally {
            return userTableData();
        }

    }

    private void doUserAction(HttpServletRequest request){
        String action = request.getParameter("useraction").toLowerCase();
        if(action.equals("delete")){
            deleteUser(request);
        }
        if(action.equals("edit")){
            editUser(request);
        }
        if(action.equals("add")){
            addUser(request);
        }
    }

    private void deleteUser(HttpServletRequest request){
        if (request.getParameter("user_id") != null) {
            Long id = Long.valueOf(request.getParameter("user_id"));
            try {
                userDAO.delete(id);
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
    }

    private  ModelAndView userTableData() throws TypeMismatchException {

        ModelAndView model = new ModelAndView();
        model.setViewName("users");

        try {
            userTableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }

        model.addObject("model",tableBuilder.getJsonDescriptionOfGoogleVizualizationDataTable(userTableData));

        return model;
    }

    private  void addUser(HttpServletRequest request){
        //check that fields are not empty
        if (isParametersValid(request)) {
            //create user and add to database
            User user = createUserFromRequest(request);
            storeUserToDatabase(user);
        }
    }

    protected void editUser(HttpServletRequest request){
        try {
            User user = userDAO.getById(Long.valueOf(request.getParameter("user_id")));

            user.setUser_type(Long.valueOf(request.getParameter("user_type")));
            user.setLogin(request.getParameter("userlogin"));
            user.setPassword(request.getParameter("userpassword"));
            user.setComments(request.getParameter("comments"));

            userDAO.update(user);


        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    protected boolean isParametersValid(HttpServletRequest req) {
        return !req.getParameter("user_type").trim().isEmpty() &&
                !req.getParameter("userlogin").trim().isEmpty() &&
                !req.getParameter("userpassword").trim().isEmpty();
    }

    private void storeUserToDatabase(User user) {
        try {
            userDAO.create(user);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    protected User createUserFromRequest(HttpServletRequest req) {
        User user = new User();
        String user_type = req.getParameter("user_type");
        String login = req.getParameter("userlogin");
        String password = req.getParameter("userpassword");
        String comments = req.getParameter("comments");

        user.setComments(comments);
        user.setLogin(login);
        user.setPassword(password);
        user.setUser_type(Long.parseLong(user_type));
        return user;
    }


}

