package lv.javaguru.java2.servlets.mvc.models;

/**
 * Created by Juris on 05.12.2014.
 */
public class MVCActorModel extends MVCModel {

    private Integer interval;

    public MVCActorModel(String view, Object data, Integer interval) {
        super(view, data);
        this.interval=interval;
    }

    public Integer getInterval() {
        return interval;
    }
}
