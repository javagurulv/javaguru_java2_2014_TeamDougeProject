package lv.javaguru.java2.domain;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Sergo on 16.10.2014.
 */


public class Film {

    private Integer film_id = 0;
    private String title = "";
    private String description = "";
    private Integer release_year = 0;
    private String language = "";
    private String original_language = "";
    private Integer rental_duration = 0;
    private Float rental_rate = null;
    private Integer length = 0;
    private Float replacement_cost = null;
    private String rating = "";
    private String special_features = "";
    private Date last_update = null;
    private Map<String, String> infoMap;

    public Film()
    {
        infoMap = new LinkedHashMap<String, String>();

    }

    private String convertFieldNameToKEY(String fieldName)
    {
        return fieldName.toUpperCase().replace("_"," ");

    }
    private void autoInitInfoMap() {

        infoMap.clear();
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);

                String f_name = convertFieldNameToKEY(field.getName());
                if (!f_name.equals("INFOMAP")) {
                    String value = "";
                    Object a = this.getClass().getDeclaredField(field.getName()).get(this);
                    if (!(a == null)) {
                        value = a.toString();
                    }
                    infoMap.put(f_name, value);
                }
            }
            catch (NoSuchFieldException e)
            {
                System.out.println(e.getMessage());
            }
            catch (IllegalAccessException e)
            {
                System.out.println(e.getMessage());
            }

        }


    }

    private void putDataToInfoMap(Field field, Object value)
    {
        String f_name = field.getName();
        f_name = f_name.toUpperCase();
        f_name = f_name.replace("_"," ");
        infoMap.put(f_name, value.toString());
    }

    public void setRental_rate(Float rental_rate) {
        this.rental_rate = rental_rate;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {

        this.rating = rating;


    }

    public Float getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(float replacement_cost) {
        this.replacement_cost = replacement_cost;

    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;

    }

    public Float getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(float rental_rate)  {
        this.rental_rate = rental_rate;

    }

    public int getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(int rental_duration)  {
        this.rental_duration = rental_duration;

    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setReplacement_cost(Float replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String special_features) {
        this.special_features = special_features;
    }

    public Map<String, String> getInfoMap()  {

        autoInitInfoMap();
        return infoMap;
    }
}
