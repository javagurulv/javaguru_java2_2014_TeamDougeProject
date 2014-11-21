package lv.javaguru.java2.domain;


import java.util.Date;
import java.util.Map;

/**
 * Created by Juris on 17.10.2014.
 */
public class Actor extends DBDomain{
    protected Integer actor_id = 0;
    protected String first_name = "";
    protected String last_name = "";
    protected Date last_update = null;

    public Actor(){
        super();
    }

    public Integer getActor_id() {
        return actor_id;
    }

    public void setActor_id(Integer actor_id) {
        this.actor_id = actor_id;
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

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public Map<String, String> getInfoMap()  {
        buildInfoMap();
        return infoMap;
    }
}


