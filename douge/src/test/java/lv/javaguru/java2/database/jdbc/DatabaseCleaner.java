package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viktor on 01/07/2014.
 */
public class DatabaseCleaner extends DAOImpl {

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("USERS");
        tableNames.add("USER_TYPES");
        tableNames.add("DASHBOARDS");
        tableNames.add("WIDGETS");
        tableNames.add("WIDGET_TYPES");
        tableNames.add("ACTOR");
        tableNames.add("CITY");
        tableNames.add("ADDRESS");
        tableNames.add("CUSTOMER");
        tableNames.add("ACTOR");
        tableNames.add("COUNTRY");
        tableNames.add("FILM");
        tableNames.add("STORE");
        tableNames.add("FILM_ACTOR");
        tableNames.add("FILM_TEXT");
        tableNames.add("LANGUAGE");
        tableNames.add("CATEGORY");
        tableNames.add("FILM_CATEGORY");
        tableNames.add("INVENTORY");
        tableNames.add("RENTAL");
        tableNames.add("STAFF");
        tableNames.add("METRICS");

        return tableNames;
    }

    public void cleanDatabase() throws DBException {
        Connection connection = null;
        for(String tableName : getTableNames()) {

            try {
                connection = getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("delete from " + tableName);
                preparedStatement.executeUpdate();
            } catch (Throwable e) {
                System.out.println("Exception while execute cleanDatabase() for table " + tableName);
                e.printStackTrace();
                throw new DBException(e);
            } finally {
                closeConnection(connection);
            }
        }
    }

}
