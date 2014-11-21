package lv.javaguru.java2.domain;

/**
 * Created by Sergo on 21.11.2014.
 */
public class AmountRentalCount extends DBDomain {
    protected String filmCategory;
    protected String Amount;
    protected String RentalCount;

    public String getFilmCategory() {
        return filmCategory;
    }

    public void setFilmCategory(String filmCategory) {
        this.filmCategory = filmCategory;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getRentalCount() {
        return RentalCount;
    }

    public void setRentalCount(String rentalCount) {
        RentalCount = rentalCount;
    }
}
