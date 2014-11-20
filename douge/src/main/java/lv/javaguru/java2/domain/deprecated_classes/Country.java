package lv.javaguru.java2.domain.deprecated_classes;

import java.util.Date;

/**
 * Created by Sergo on 16.10.2014.
 */
@Deprecated
public class Country {
    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    private int country_id;
    private String country;
    private Date last_update;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }



}
