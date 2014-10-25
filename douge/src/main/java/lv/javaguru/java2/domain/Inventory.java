package lv.javaguru.java2.domain;

import java.util.Date;

/**
 * Created by Juris on 24.10.2014.
 */
public class Inventory {
    int inventory_id;
    Short film_id;
    int store_id;
    Date last_update;

    public Short getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Short film_id) {
        this.film_id = film_id;
    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }
}
