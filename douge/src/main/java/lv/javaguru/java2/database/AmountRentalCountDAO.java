package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.AmountRentalCount;
import lv.javaguru.java2.domain.DBDomain;

import java.util.List;

/**
 * Created by Sergo on 21.11.2014.
 */
public interface AmountRentalCountDAO {
    List<AmountRentalCount> getAmountRentalCountData() throws DBException;
}