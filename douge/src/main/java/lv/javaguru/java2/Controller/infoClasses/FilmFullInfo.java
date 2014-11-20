package lv.javaguru.java2.Controller.infoClasses;


import lv.javaguru.java2.domain.Film;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Radchuk Sergey on 02.11.2014.
 */
public class FilmFullInfo {

    private Integer id;
    private String title;
    private String description;
    private Integer releaseYear;
    private String language;
    private String originalLanguage;


    public FilmFullInfo(Film film, LanguagesList languagesList){
        prepareInfo(film,languagesList);
    }

    private void prepareInfo(Film film, LanguagesList languagesList)
    {
        this.id = film.getFilm_id();
        this.title = film.getTitle();
        this.description = film.getDescription();
        this.releaseYear = film.getRelease_year();
        this.language = film.getLanguage();
        this.originalLanguage = film.getOriginal_language();
    }



    public Map<String, String> getFilmInfo()
    {

        Map<String, String> filmInfo = new LinkedHashMap<String, String>();
        filmInfo.put("id",id.toString() );
        filmInfo.put("Title", title);
        filmInfo.put("Description", description);
        filmInfo.put("Release Year", releaseYear.toString());
        filmInfo.put("Language", language);
        filmInfo.put("Original Language", originalLanguage);
        return filmInfo;
    }


}
