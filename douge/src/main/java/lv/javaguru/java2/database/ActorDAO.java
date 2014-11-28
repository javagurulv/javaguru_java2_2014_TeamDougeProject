package lv.javaguru.java2.database;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.Actor;
import lv.javaguru.java2.domain.DomainWidgetContent;

import java.util.List;

/**
 * Created by Juris on 17.10.2014.
 */
public interface ActorDAO {

    Actor getByID(Short id) throws DBException;

    List<DomainWidgetContent> getAll() throws  DBException;

    List<DomainWidgetContent> getAllFromRange(int from, int amount) throws DBException;

    Integer getActorsAmount() throws DBException;


}
