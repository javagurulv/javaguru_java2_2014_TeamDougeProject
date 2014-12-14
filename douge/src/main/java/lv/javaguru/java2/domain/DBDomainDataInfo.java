package lv.javaguru.java2.domain;

import com.google.visualization.datasource.datatable.ValueFormatter;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.ibm.icu.util.ULocale;

/**
 * Created by Sergo on 25.11.2014.
 */
public class DBDomainDataInfo {
    private String dataName;
    private ValueType dataType;
    private String dataValue;

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public ValueType getDataType() {
        return dataType;
    }

    public void setDataType(ValueType dataType) {
        this.dataType = dataType;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String toString(){
        return dataName + " " + dataValue + " " + dataType;
    }

    public DBDomainDataInfo(){
    }

    public  DBDomainDataInfo(String dataName, Object dataValue){

        this.dataName = dataName;
        if(dataValue.getClass().isInstance(Boolean.class)){
            this.dataType = ValueType.BOOLEAN;

        }
        else if(Number.class.isAssignableFrom(dataValue.getClass())){
            this.dataType = ValueType.NUMBER;
        }
        else{
            this.dataType = ValueType.TEXT;
        }

       this.dataValue = dataValue.toString();

    }
}
