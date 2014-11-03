package lv.javaguru.java2.Controller.Builders;

import lv.javaguru.java2.Controller.TableData;
import lv.javaguru.java2.Controller.infoClasses.FilmFullInfo;
import lv.javaguru.java2.Controller.infoClasses.LanguagesList;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Film;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Radchuk Sergey on 11/3/2014.
 */
public class FilmInfoBuilder implements TableData{
    private ArrayList<Map<String, String>> tableData = null;

    protected void buildFilmsInfo() throws DBException {
        tableData = new ArrayList<Map<String, String>>();
        LanguagesList languagesList = new LanguagesList( DAOFactory.getInstance().getLanguageDAO().getAll());
        List<Film> filmList = DAOFactory.getInstance().getFilmDAO().getAll();
        for (int i = 0; i < filmList.size() ; i++) {
            FilmFullInfo filmFullInfo = new FilmFullInfo(filmList.get(i), languagesList);
            tableData.add(filmFullInfo.getFilmInfo());
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
