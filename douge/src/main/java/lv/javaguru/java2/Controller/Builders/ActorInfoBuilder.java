package lv.javaguru.java2.Controller.Builders;

import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.WidgetTableData;
import lv.javaguru.java2.Controller.infoClasses.ActorFullInfo;
import lv.javaguru.java2.database.ActorDAO;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Actor;
import lv.javaguru.java2.domain.DomainWidgetContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Juris on 04.11.2014.
 */
@Component("actorTableData")
public class ActorInfoBuilder implements WidgetTableData {

    private List<DomainWidgetContent> tableData = null;
    @Autowired
    private ActorDAO actorDAO;

    protected void buildActorInfo() throws DBException{
        tableData = actorDAO.getAll();
    }


    @Override
    public List<DomainWidgetContent> getWidgetTableData() {
        return tableData;
    }

    @Override
    public void buildTableData() throws DBException {
        buildActorInfo();
    }

    @Override
    public void buildTableData(Map<String, String> params) throws DBException {
        Integer interval = 1;
        Integer from = 1;
        Integer to = 10;

        if (params.keySet().contains("interval")&&params.get("interval")!=null){
            try {
                interval = Integer.valueOf(params.get("interval"));
                from = interval *10;
                to = from+10;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        tableData = actorDAO.getAllFromRange(from,to);
    }
}
