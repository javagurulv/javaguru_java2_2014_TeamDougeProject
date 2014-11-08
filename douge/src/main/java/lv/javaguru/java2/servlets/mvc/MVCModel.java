package lv.javaguru.java2.servlets.mvc;

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

    public String getView() {
        return view;
    }

    public Object getData() {
        return data;
    }

}
