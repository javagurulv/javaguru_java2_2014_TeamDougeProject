package lv.javaguru.java2.domain;

/**
 * Created by Sergo on 21.11.2014.
 */
public class AmountRentalCount extends DBDomain {
    protected String filmCategory;
    protected String amount;
    protected String rentalCount;

    public String getFilmCategory() {
        return filmCategory;
    }

    public void setFilmCategory(String filmCategory) {
        this.filmCategory = filmCategory;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRentalCount() {
        return rentalCount;
    }

    public void setRentalCount(String rentalCount) {
        this.rentalCount = rentalCount;
    }

    public AmountRentalCount()
    {
        super();
    }
}
