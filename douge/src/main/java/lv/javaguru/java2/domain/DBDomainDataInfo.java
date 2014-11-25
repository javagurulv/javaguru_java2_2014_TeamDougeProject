package lv.javaguru.java2.domain;

/**
 * Created by Sergo on 25.11.2014.
 */
public class DBDomainDataInfo {
    String dataName;
    String dataType;
    String dataValue;

    public String toString(){
        return dataName + " " + dataValue + " " + dataType;
    }
}
