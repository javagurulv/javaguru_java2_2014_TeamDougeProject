package lv.javaguru.java2.servlets.mvc;

import lv.javaguru.java2.servlets.mvc.controllrers.*;
import lv.javaguru.java2.servlets.mvc.models.*;
import lv.javaguru.java2.servlets.mvc.spring.SpringAppConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Juris on 08.11.2014.
 */
public class MVCFilter implements Filter {

    private static Logger logger = Logger.getLogger(MVCFilter.class.getName());

    private Map<String, MVCController> controllerMap;

    private ApplicationContext springContext;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            springContext =
                    new AnnotationConfigApplicationContext(SpringAppConfig.class);
        } catch (BeansException e) {
            logger.log(Level.INFO, "Spring context failed to start", e);
        }

        controllerMap = new HashMap<String,MVCController>();
        controllerMap.put("/hello", getBean(HelloWorldController.class));
        controllerMap.put("/actors", getBean(ActorTableController.class));
        controllerMap.put("/films", getBean(FilmTableController.class));
        controllerMap.put("/index", getBean(IndexController.class));
        controllerMap.put("/login", getBean(LoginController.class));
        controllerMap.put("/logout", getBean(LogoutController.class));
        controllerMap.put("/adduser", getBean(AddUserController.class));
    }

    private MVCController getBean(Class clazz){
        return (MVCController) springContext.getBean(clazz);
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        String contextURI = req.getServletPath();
        if (contextURI.equals("/") || contextURI.equals("/favicon.ico")){contextURI = "/index";}

        System.out.println("contextURI " + contextURI);

        //String path = ((HttpServletRequest) request).getRequestURI();
        //System.out.println(path);


        MVCController controller = controllerMap.get(contextURI);
        MVCModel model = controller.processRequest(req, resp);


        req.setAttribute("model", model.getData());
        ServletContext context = req.getServletContext();
        System.out.println("View: "+model.getView());
        RequestDispatcher requestDispacher = context.getRequestDispatcher(model.getView());
        requestDispacher.forward(req, resp);
    }

    @Override
    public void destroy() {

    }
}
