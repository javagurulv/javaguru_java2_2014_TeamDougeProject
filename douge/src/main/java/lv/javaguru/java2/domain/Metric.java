package lv.javaguru.java2.domain;

/**
 * Created by Radchuk on 11/10/2014.
 */
public class Metric {
    private Integer id;
    private  String type;
    private Integer compatibility;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(Integer compatibility) {
        this.compatibility = compatibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
