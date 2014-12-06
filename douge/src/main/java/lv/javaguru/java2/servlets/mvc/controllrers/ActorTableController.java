package lv.javaguru.java2.servlets.mvc.controllrers;
import com.google.visualization.datasource.base.TypeMismatchException;
import lv.javaguru.java2.Controller.Builders.GoogleVisualizationDataTableBuilder;
import lv.javaguru.java2.Controller.WidgetTableData;
import lv.javaguru.java2.database.ActorDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.servlets.mvc.MVCController;
import lv.javaguru.java2.servlets.mvc.models.MVCActorModel;
import lv.javaguru.java2.servlets.mvc.models.MVCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Juris on 08.11.2014.
 */
@Component
public class ActorTableController implements MVCController {

    @Autowired @Qualifier("actorTableData")
    private WidgetTableData actorTableData;
    @Autowired
    GoogleVisualizationDataTableBuilder tableBuilder;
    @Autowired
    ActorDAO actorDAO;

    @Override
    public MVCModel processRequest(HttpServletRequest request, HttpServletResponse response) {

        Map<String,String> params = new HashMap<String, String>();
        if (request.getParameter("interval")!=null){
            params.put("interval",request.getParameter("interval").toString());
        }


            System.out.println(request.getParameterMap().toString());


        try {
            actorTableData.buildTableData(params);
        } catch (DBException e) {
            e.printStackTrace();
        }

        try {
            tableBuilder.prepareInfo(actorTableData);
        } catch (TypeMismatchException e) {
            e.printStackTrace();
        }

        MVCActorModel mvcActorModel = null;
        try {
            mvcActorModel = new MVCActorModel("/jsp/tableconverter.jsp", tableBuilder.getJsonDescriptionOfGoogleVizualizationDataTable(),actorDAO.getActorsAmount()/10);
        } catch (DBException e) {
            e.printStackTrace();
        }

        return new MVCModel("/jsp/tableconverter.jsp",mvcActorModel);
    }
}
