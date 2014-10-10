package lv.javaguru.java2.database;


import lv.javaguru.java2.domain.WidgetType;
import java.util.List;
/**
 * Created by Radchuk on 10/10/2014.
 */
public interface WidgetTypeDAO {
    void create (WidgetType widgetType)throws DBException;

    WidgetType getById(Long id)throws DBException;

    void update (WidgetType widgetType) throws DBException;

    void delete(Long id) throws DBException;

    List<WidgetType> getAll()throws DBException;
}
