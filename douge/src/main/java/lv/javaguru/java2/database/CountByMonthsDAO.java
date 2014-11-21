package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.CountByMonths;

import java.util.List;

/**
 * Created by Sergo on 21.11.2014.
 */
public interface CountByMonthsDAO {
    List<CountByMonths> geCountByMonths() throws DBException;

}
