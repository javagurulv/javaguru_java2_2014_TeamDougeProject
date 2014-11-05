package lv.javaguru.java2.Controller.infoClasses;

import lv.javaguru.java2.domain.Actor;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Juris on 04.11.2014.
 */
public class ActorFullInfo {
    private Short id;
    private String first_name;
    private String last_name;
    private Date last_update;

    public ActorFullInfo(Actor actor) {
        prepareInfo(actor);
    }

    private void prepareInfo(Actor actor){
        this.id=actor.getActor_id();
        this.first_name=actor.getFirst_name();
        this.last_name=actor.getLast_name();
        this.last_update=actor.getLast_update();
    }

    public Map<String, String> getActorInfo(){
        Map<String, String> actorInfo = new LinkedHashMap<String, String>();
        actorInfo.put("id",id.toString());
        actorInfo.put("First Name",first_name);
        actorInfo.put("Last Name",last_name);
        actorInfo.put("Last Update",last_update.toString());
        return  actorInfo;
    }

}
