package lv.javaguru.java2.Controller.Builders;

import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.infoClasses.ActorFullInfo;
import lv.javaguru.java2.database.ActorDAO;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Juris on 04.11.2014.
 */
@Component("actorTableData")
public class ActorInfoBuilder implements TableData {

    private ArrayList<Map<String, String>> tableData = null;
    @Autowired
    private ActorDAO actorDAO;


    protected void buildActorsInfo() throws DBException {
        tableData = new ArrayList<Map<String, String>>();
        List<Actor> actorList = actorDAO.getAll();
        for (int i = 0; i < actorList.size() ; i++) {
            tableData.add(actorList.get(i).getInfoMap());
        }
    }

    @Override
    public ArrayList<Map<String, String>> getTableData() {
        return tableData;
    }

    @Override
    public void buildTableData() throws DBException {
        buildActorsInfo();
    }
}
