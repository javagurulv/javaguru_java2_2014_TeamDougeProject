package lv.javaguru.java2.domain;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Sergo on 21.11.2014.
 */
public class DBDomain {
    protected Map<String, String> infoMap;

    protected String convertFieldNameToKEY(String fieldName)
    {
        return fieldName.toUpperCase().replace("_"," ");

    }

    public DBDomain(){
        infoMap = new LinkedHashMap<String, String>();
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

    public Map<String, String> getInfoMap()
    {
        buildInfoMap();
        return infoMap;
    }
}
