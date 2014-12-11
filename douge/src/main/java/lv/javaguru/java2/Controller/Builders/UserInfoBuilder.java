package lv.javaguru.java2.Controller.Builders;

import lv.javaguru.java2.Controller.WidgetTableData;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.DomainWidgetContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Juris on 10.12.2014.
 */
@Component("userTableData")
public class UserInfoBuilder implements WidgetTableData {

    private List<DomainWidgetContent> tableData = null;
    @Autowired @Qualifier("ORM_UserDAO")
    private UserDAO userDAO;

    protected void buildUserInfo() throws DBException{
        tableData = userDAO.getAll();
    }


    @Override
    public List<DomainWidgetContent> getWidgetTableData() {
        return tableData;
    }

    @Override
    public void buildTableData() throws DBException {
        buildUserInfo();
    }

    @Override
    public void buildTableData(Map<String, String> params) throws DBException {
        buildUserInfo();
    }
}
