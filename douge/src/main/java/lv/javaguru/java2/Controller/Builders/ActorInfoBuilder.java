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
        buildTableData();
    }
}
