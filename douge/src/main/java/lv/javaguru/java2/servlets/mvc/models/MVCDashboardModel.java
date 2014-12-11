package lv.javaguru.java2.servlets.mvc.models;
import lv.javaguru.java2.domain.Dashboard;

/**
 * Created by user on 10-Dec-14.
 */
public class MVCDashboardModel extends MVCModel {

    private Dashboard currentDashboard;

    public MVCDashboardModel(String view, Object data, Dashboard currentDashboard) {
        super(view, data);
        this.currentDashboard = currentDashboard;
    }

    public Dashboard getCurrentDashboard() {
        return currentDashboard;
    }

}