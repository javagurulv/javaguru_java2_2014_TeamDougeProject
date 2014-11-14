package lv.javaguru.java2.servlets.mvc;

import lv.javaguru.java2.servlets.mvc.controllrers.*;
import lv.javaguru.java2.servlets.mvc.models.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Juris on 08.11.2014.
 */
public class MVCFilter implements Filter {

    private Map<String, MVCController> controllerMap;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        controllerMap = new HashMap<String,MVCController>();
        controllerMap.put("/hello", new HelloWorldController());
        controllerMap.put("/actors",new ActorTableController());
        controllerMap.put("/films",new FilmTableCotroller());
        controllerMap.put("/index", new IndexController());
        controllerMap.put("/login", new LoginController());
        controllerMap.put("/logout", new LogoutController());
        controllerMap.put("/adduser", new AddUserController());
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
