package lv.javaguru.java2.Controller.Classes;

import lv.javaguru.java2.database.DAOFactory;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FilmDAO;
import lv.javaguru.java2.domain.Actor;
import lv.javaguru.java2.domain.Film;
import lv.javaguru.java2.domain.Film_Actor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergo on 02.11.2014.
 */
public class FullFilmInfo {

    private Integer id;
    private String title;
    private String description;
    private Integer releaseYear;
    private String language;
    private String originalLanguage;

    private Film film;


    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) throws DBException {
        this.id = film.getFilm_id();
        this.film = film;
        this.title = this.film.getTitle();
        this.releaseYear = this.film.getRelease_year();
        this.language = DAOFactory.getInstance().getLanguageDAO().getById(film.getLanguage_id()).getName();
        this.originalLanguage = DAOFactory.getInstance().getLanguageDAO().getById(film.getOriginal_language_id()).getName();
    }

    public Map<String, String> getFilmInfo()
    {
        Map<String, String> filmInfo = new HashMap<String, String>();
        filmInfo.put("id",id.toString() );
        filmInfo.put("Title", title);
        filmInfo.put("Description", description);
        filmInfo.put("Release Year", releaseYear.toString());
        filmInfo.put("Language", language);
        filmInfo.put("Original Language", originalLanguage);
        return filmInfo;
    }


}
