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

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ModelAndView processRequest(HttpServletRequest request, HttpServletResponse response) throws TypeMismatchException {

        if (request.getParameter("delete") != null) {
           deleteUser(request);
        }

        if (request.getParameter("adduser")!=null) {
            addUser(request);
        }

        if (request.getParameter("edituser")!=null){
            editUser(request);
        }



        return userTableData();
    }

    private void deleteUser(HttpServletRequest request){
        if (request.getParameter("useriddelete") != null) {
            Long id = Long.valueOf(request.getParameter("useriddelete"));
            try {
                userDAO.delete(id);
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
    }

    private  ModelAndView userTableData(){

        ModelAndView model = new ModelAndView();
        model.setViewName("users");

        try {
            userTableData.buildTableData();
        } catch (DBException e) {
            e.printStackTrace();
        }

        try {
            tableBuilder.prepareInfo(userTableData);
        } catch (TypeMismatchException e) {
            e.printStackTrace();
        }

        model.addObject(tableBuilder.getJsonDescriptionOfGoogleVizualizationDataTable());

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
            User user = userDAO.getById(Long.valueOf(request.getParameter("useridedit")));

            user.setUser_type(Long.valueOf(request.getParameter("user_typee")));

            if (request.getParameter("logine")!="") {
                user.setLogin(request.getParameter("logine"));
            }
            if (request.getParameter("passwdee")!="") {
                user.setPassword(request.getParameter("passwde"));
            }
            user.setComments(request.getParameter("commentse"));
            userDAO.update(user);


        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    protected boolean isParametersValid(HttpServletRequest req) {
        return !req.getParameter("user_typed").trim().isEmpty() &&
                !req.getParameter("logind").trim().isEmpty() &&
                !req.getParameter("passwdd").trim().isEmpty();
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
        String user_type = req.getParameter("user_typed");
        String login = req.getParameter("logind");
        String password = req.getParameter("passwdd");
        String comments = req.getParameter("commentsd");

        user.setComments(comments);
        user.setLogin(login);
        user.setPassword(password);
        user.setUser_type(Long.parseLong(user_type));
        return user;
    }


}

