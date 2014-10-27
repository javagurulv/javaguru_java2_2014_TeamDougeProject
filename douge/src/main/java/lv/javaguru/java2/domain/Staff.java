package lv.javaguru.java2.domain;

import com.mysql.jdbc.Blob;

import javax.sql.rowset.serial.SerialBlob;
import java.util.Date;

/**
 * Created by Juris on 25.10.2014.
 */
public class Staff{
    int staff_id;
    String first_name;
    String last_name;
    Short address_id;



    SerialBlob picture;
    String email;
    int store_id;
    int active;
    String username;
    String password;
    Date last_update;

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Short getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Short address_id) {
        this.address_id = address_id;
    }

    public void setPicture(SerialBlob picture) {
        this.picture = picture;
    }

    public SerialBlob getPicture() {
        return picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }
}
