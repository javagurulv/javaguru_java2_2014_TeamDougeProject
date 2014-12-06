package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Sergo on 06.12.2014.
 */
@Entity
@Table(name = "data_united")
public class ChartData {

    /*`rental_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `StaffName` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `FilmRating` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `FilmCategory` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `amount` decimal(5,2) NOT NULL,
  `Date` date DEFAULT NULL,
  `Month` varchar(9) CHARACTER SET utf8 DEFAULT NULL,
  `WeekNumber` int(2) DEFAULT NULL,*/
    @Column(name="rental_id",columnDefinition = "int(11)")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rental_id;

    @Column(name = "StaffName", columnDefinition = "varchar(25)")
    private String staffName;

    @Column(name = "FilmRating", columnDefinition = "varchar(5)")
    private String filmRating;

    @Column(name = "FilmCategory", columnDefinition = "varchar(25)")
    private String filmCategory;

    @Column(name = "amount", columnDefinition = "decimal(5,2)")
    private Double amount;

    @Column(name = "date" , columnDefinition = "Date")
    private Date date;

    @Column(name = "Month" , columnDefinition = "varchar(9)")
    private String month;

    @Column(name = "WeekNumber", columnDefinition = "int(2)")
    private Integer weekNumber;


    public Long getRental_id() {
        return rental_id;
    }

    public void setRental_id(Long rental_id) {
        this.rental_id = rental_id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getFilmRating() {
        return filmRating;
    }

    public void setFilmRating(String filmRating) {
        this.filmRating = filmRating;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }
}
