package lv.javaguru.java2.database;


import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Widget;
import java.util.List;
/**
 * Created by Radchuk on 10/10/2014.
 */
public interface WidgetDAO {
     Widget getByID(Long id)throws DBException;

     void create(Widget widget)throws DBException;

     void delete(Long id) throws DBException;

     void update(Widget widget)throws DBException;

     List<Widget> getAll() throws DBException;

}
