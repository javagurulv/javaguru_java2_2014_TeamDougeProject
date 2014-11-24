package lv.javaguru.java2.Controller.Builders;

import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FilmDAO;
import lv.javaguru.java2.domain.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Radchuk Sergey on 11/3/2014.
 */
@Component("filmTableData")

public class FilmInfoBuilder implements TableData{
    private ArrayList<Map<String, String>> tableData = null;

    @Autowired
    private FilmDAO filmDAO;

    protected void buildFilmsInfo() throws DBException {
        tableData = new ArrayList<Map<String, String>>();

        List<Film> filmList = filmDAO.getAll();
        for (int i = 0; i < filmList.size() ; i++) {

            tableData.add(filmList.get(i).getInfoMap());
        }
    }

    @Override
    public ArrayList<Map<String, String>> getTableData() {
        return tableData;
    }

    @Override
    public void buildTableData() throws DBException {
        buildFilmsInfo();
    }
}
