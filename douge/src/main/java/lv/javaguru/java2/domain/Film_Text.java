package lv.javaguru.java2.domain;

/**
 * Created by Sergo on 21.10.2014.
 */
public class Film_Text {

    /*CREATE TABLE film_text
(
    film_id SMALLINT PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    description LONGTEXT
);
CREATE INDEX idx_title_description ON film_text (title, description);
*/
    private int film_id;
    private String title;
    private String description;

    public Film_Text(int film_id, String title, String description) {
        this.film_id = film_id;
        this.title = title;
        this.description = description;
    }

    public Film_Text() {
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
