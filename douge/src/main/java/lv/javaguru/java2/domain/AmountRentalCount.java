package lv.javaguru.java2.domain;

/**
 * Created by Sergo on 21.11.2014.
 */
public class AmountRentalCount extends DBDomain {
    protected String filmCategory;
    protected Double amount;
    protected Integer rentalCount;

    public String getFilmCategory() {
        return filmCategory;
    }

    public void setFilmCategory(String filmCategory) {
        this.filmCategory = filmCategory;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getRentalCount() {
        return rentalCount;
    }

    public void setRentalCount(Integer rentalCount) {
        this.rentalCount = rentalCount;
    }

    public AmountRentalCount()
    {
        super();
    }
}
