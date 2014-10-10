package lv.javaguru.java2.database;

/**
 * Created by Radchuk on 10/10/2014.
 */

import lv.javaguru.java2.domain.Dashboard;
import java.util.List;

public interface DashboardDAO {
    void create(Dashboard dashboard)throws DBException;

    Dashboard getById(Long id) throws DBException;

    void update (Dashboard dashboard) throws DBException;

    void delete (Long id) throws DBException;

    List<Dashboard> getAll() throws DBException;

}
