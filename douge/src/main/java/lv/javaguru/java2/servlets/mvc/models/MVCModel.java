package lv.javaguru.java2.servlets.mvc.models;

/**
 * Created by Juris on 08.11.2014.
 */
public class MVCModel {

    private String view;
    private Object data;

    public MVCModel(String view, Object data){
        this.view = view;
        this.data = data;
    }

    public MVCModel(String view){
        this.view = view;
        this.data = new Object();
    }

    public String getView() {
        return view;
    }

    public Object getData() {
        return data;
    }

}
