package lv.javaguru.java2.domain;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Sergo on 16.10.2014.
 */


public class Film extends DBDomain {

    protected Integer film_id = 0;
    protected String description = "";
    protected String title = "";
    protected Integer release_year = 0;
    protected String language = "";
    protected String original_language = "";
    protected Integer rental_duration = 0;
    protected Float rental_rate = null;
    protected Integer length = 0;
    protected Float replacement_cost = null;
    protected String rating = "";
    protected String special_features = "";
    protected Date last_update = null;


    public Film()
    {
        super();

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


}
