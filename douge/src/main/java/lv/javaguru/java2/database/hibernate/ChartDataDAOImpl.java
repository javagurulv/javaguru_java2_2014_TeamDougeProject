package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.ChartDataDAO;
import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.domain.ChartData;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergo on 06.12.2014.
 */
@Component("ORM_ChartDataDAO")
public class ChartDataDAOImpl implements ChartDataDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ChartData> getAll() throws DBException {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(ChartData.class).list();
    }

    @Override
    public List<ChartData> getByParams(Map<String, String> params) throws DBException {
        return null;
    }


    @Override
    public Integer getRecordsAmount() throws DBException {
        Integer recordsAmount = 0;
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.createCriteria(ChartData.class)
                .setProjection(
                            Projections.projectionList()
                            .add( Projections.rowCount(), "Count" )).uniqueResult();
        recordsAmount = Integer.valueOf(obj.toString());

        return recordsAmount;
    }

    @Override
    public List<Object> getByQueryText(String queryText) throws DBException {
       Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery(queryText);

        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List result = query.list();
        return result;
    }
}
