package lv.javaguru.java2.domain.deprecated_classes;

import lv.javaguru.java2.domain.DBDomain;

/**
 * Created by Sergo on 21.11.2014.
 */
@Deprecated
public class CountByMonths extends DBDomain {
    protected String monthName;
    protected Integer count;

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public CountByMonths()
    {
        super();
    }
}
