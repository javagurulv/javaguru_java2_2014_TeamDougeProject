package lv.javaguru.java2.Controller.Builders;

import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.infoClasses.ActorFullInfo;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Juris on 04.11.2014.
 */
public class ActorInfoBuilder implements TableData {

    private ArrayList<Map<String, String>> tableData = null;


    protected void buildActorsInfo() throws DBException {
        tableData = new ArrayList<Map<String, String>>();
        List<Actor> actorList = DAOFactory.getInstance().getActorDAO().getAll();
        for (int i = 0; i < actorList.size() ; i++) {
            ActorFullInfo actorFullInfo = new ActorFullInfo(actorList.get(i));
            tableData.add(actorFullInfo.getActorInfo());
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
