package lv.javaguru.java2.Controller;

import lv.javaguru.java2.Controller.Builders.ActorInfoBuilder;
import lv.javaguru.java2.Controller.Builders.FilmInfoBuilder;

/**
 * Created by Radchuk on 11/4/2014.
 */
public class TableDataFactory {
    // Singleton realization
    private TableDataFactory(){

    }

    private static TableDataFactory instance = null;

    public static TableDataFactory getInstance(){
        if (instance == null)
        {
            instance = new TableDataFactory();
        }
        return instance;
    }
    //--------------------------------------------------

    public TableData getFilmTableData(){
        return new FilmInfoBuilder();
    }
    public TableData getActorTableData(){ return new ActorInfoBuilder();}
}
