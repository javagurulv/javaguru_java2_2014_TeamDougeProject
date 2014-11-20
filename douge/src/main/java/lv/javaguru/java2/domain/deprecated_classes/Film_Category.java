package lv.javaguru.java2.domain.deprecated_classes;

import java.util.Date;

/**
 * Created by Juris on 24.10.2014.
 */
@Deprecated
public class Film_Category {
   private Short film_id;
   private int category_id;
   private Date last_update;

    public Short getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Short film_id) {
        this.film_id = film_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }
}
