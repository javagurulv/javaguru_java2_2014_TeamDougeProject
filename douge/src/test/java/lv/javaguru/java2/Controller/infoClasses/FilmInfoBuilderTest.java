/*
package lv.javaguru.java2.Controller.infoClasses;

*/
/**
 * Created by Radchuk on 11/3/2014.
 *//*

import static org.junit.Assert.*;

import lv.javaguru.java2.Controller.Builders.FilmInfoBuilder;
import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FilmDAO;
import lv.javaguru.java2.database.jdbc.DatabaseCleaner;
import lv.javaguru.java2.domain.Film;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
@Ignore
public class FilmInfoBuilderTest {



   @Test
    public void testBuildFilmTable() throws DBException {
       new DatabaseCleaner().cleanDatabase();

       FilmDAO filmDAO = DAOFactory.getInstance().getFilmDAO();

       int count = filmDAO.getAll().size();


       FilmInfoBuilder filmInfoBuilder = new FilmInfoBuilder();
       filmInfoBuilder.buildTableData();

       ArrayList<Map<String, String>> tableData = filmInfoBuilder.getTableData();

       assertEquals(tableData.size(),count);


   }

}
*/
