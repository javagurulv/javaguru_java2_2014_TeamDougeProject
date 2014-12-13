package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.ChartData;

import java.util.List;
import java.util.Map;

/**
 * Created by Sergo on 06.12.2014.
 */
public interface ChartDataDAO {

    List<ChartData> getAll() throws DBException;

    List<ChartData> getByParams(Map<String,String> params) throws DBException;

    Integer getRecordsAmount() throws DBException;

    public List<Object[]> getByQueryText(String queryText) throws DBException;
}
