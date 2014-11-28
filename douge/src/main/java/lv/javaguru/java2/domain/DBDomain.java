package lv.javaguru.java2.domain;

import com.google.visualization.datasource.datatable.value.ValueType;

import java.lang.reflect.Field;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Sergo on 21.11.2014.
 */
public class DBDomain implements DomainWidgetContent{
    protected Map<String, String> infoMap;

    protected Map<String,DBDomainDataInfo> fullInfoList;

    protected String convertFieldNameToKEY(String fieldName)
    {
        return fieldName.toUpperCase().replace("_"," ");

    }

    public DBDomain(){
        infoMap = new LinkedHashMap<String, String>();
        fullInfoList = new LinkedHashMap<String, DBDomainDataInfo>();
    }

    protected void  buildInfoMap(){
        infoMap.clear();
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);

                String f_name = convertFieldNameToKEY(field.getName());
                if (!f_name.equals("INFOMAP")) {
                    String value = "";
                    Object a = this.getClass().getDeclaredField(field.getName()).get(this);
                    if (!(a == null)) {
                        value = a.toString();
                    }
                    infoMap.put(f_name, value);
                }
            }
            catch (NoSuchFieldException e)
            {
                System.out.println(e.getMessage());
            }
            catch (IllegalAccessException e)
            {
                System.out.println(e.getMessage());
            }

        }

    }

    protected void buildFullInfoList(){
        fullInfoList.clear();
        for (Field field : this.getClass().getDeclaredFields()){

            field.setAccessible(true);
            String f_name = convertFieldNameToKEY(field.getName());
            if(!f_name.equals("INFOMAP") &&!f_name.equals("FULLINFOLIST")){

                DBDomainDataInfo dbDomainDataInfo = new DBDomainDataInfo();
                dbDomainDataInfo.setDataName(f_name);
                if(field.getType().isInstance(Boolean.class)){
                    dbDomainDataInfo.setDataType(ValueType.BOOLEAN);
                    dbDomainDataInfo.setDataValue("false");
                }
                if (Number.class.isAssignableFrom(field.getType()))
                {//field.getType().isInstance(Number.class)){
                    dbDomainDataInfo.setDataType(ValueType.NUMBER);
                    dbDomainDataInfo.setDataValue("0");
                }
                else {
                    dbDomainDataInfo.setDataType(ValueType.TEXT);
                    dbDomainDataInfo.setDataValue("");
                }

                Object a = null;
                try {
                    a = this.getClass().getDeclaredField(field.getName()).get(this);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                if (!(a == null)) {
                    dbDomainDataInfo.setDataValue(a.toString());
                }
                fullInfoList.put(dbDomainDataInfo.getDataName(), dbDomainDataInfo);

            }
        }
    }

    public Map<String, String> getInfoMap()
    {
        buildInfoMap();
        return infoMap;
    }
    public Map<String, DBDomainDataInfo> getFullInfoMap(){
        buildFullInfoList();
        return fullInfoList;
    }

    @Override
    public Map<String, DBDomainDataInfo> getFullDomainInfo() {
        return getFullInfoMap();
    }
}
