package lv.javaguru.java2.domain.deprecated_classes;

import java.util.Date;

/**
 * Created by Radchuk on 10/20/2014.
 */
@Deprecated
public class Film_Actor {
    /*douge_project_user.film_actor
    * CREATE TABLE film_actor
(
    actor_id SMALLINT UNSIGNED NOT NULL,
    film_id SMALLINT UNSIGNED NOT NULL,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (actor_id, film_id)
);
CREATE INDEX idx_fk_film_id ON film_actor (film_id);
*/

    private int actor_id;
    private int film_id;
    private int record_id;
    private Date last_update;

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }
}
