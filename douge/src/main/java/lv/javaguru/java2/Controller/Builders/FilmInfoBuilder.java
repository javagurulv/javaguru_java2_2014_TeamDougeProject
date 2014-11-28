package lv.javaguru.java2.Controller.Builders;


import lv.javaguru.java2.Controller.WidgetTableData;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.FilmDAO;
import lv.javaguru.java2.domain.DomainWidgetContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

/**
 * Created by Radchuk Sergey on 11/3/2014.
 */
@Component("filmTableData")

public class FilmInfoBuilder implements WidgetTableData{
    private List<DomainWidgetContent> tableData = null;

    @Autowired
    private FilmDAO filmDAO;

    protected void buildFilmsInfo() throws DBException {
        tableData  = filmDAO.getAll();

    }

    @Override
    public List<DomainWidgetContent> getWidgetTableData() {
        return tableData;
    }

    @Override
    public void buildTableData() throws DBException {
        buildFilmsInfo();
    }

    @Override
    public void buildTableData(Map<String, String> params) throws DBException {
        buildTableData();
    }
}
