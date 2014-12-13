package lv.javaguru.java2.Controller.infoClasses;

import lv.javaguru.java2.domain.deprecated_classes.Language;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Radchuk on 11/3/2014.
 */
public class LanguagesList {
    private Map<Integer, String> langMap = null;

    public LanguagesList(List<Language> languages){
        langMap = new HashMap<Integer, String>();
        setLangMap(languages);
    }

   // Fill map of languages, key = id, Value  = Name
    private void setLangMap(List<Language> languages)
    {
        for (int i = 0; i < languages.size() ; i++) {
            langMap.put(languages.get(i).getLanguage_id(), languages.get(i).getName());
        }
    }

    public String getLanguageNameById(Integer id)
    {
        return langMap.get(id);
    }
}
