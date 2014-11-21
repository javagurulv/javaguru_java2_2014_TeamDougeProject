package lv.javaguru.java2.domain;

/**
 * Created by Sergo on 21.11.2014.
 */
public class CountByMonths extends DBDomain {
    protected String monthName;
    protected String count;

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public CountByMonths()
    {
        super();
    }
}
