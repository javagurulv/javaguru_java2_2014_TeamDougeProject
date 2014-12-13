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

    private List<Table> getTableNames() {
        List<Table> tables = new ArrayList<Table>();

        tables.add(new Table("USERS", "id", 1));
        tables.add(new Table("USER_TYPES", "id",1));
        tables.add(new Table("DASHBOARDS", "id", 1));
        tables.add(new Table("WIDGETS" , "id", 1));
        tables.add(new Table("WIDGET_TYPES", "id", 1));
        tables.add(new Table("METRICS", "id", 1));
        tables.add(new Table("METRICS_SETS","id",1));
       // List<String> tableNames = new ArrayList<String>();

        return tables;
    }

    public void cleanDatabase() throws DBException {
        Connection connection = null;
        for (Table table : getTableNames()){
            try{
                connection = getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement("delete  from " + table.tableName + " where " + table.primaryKey +  ">= " + table.initialAutoIncrement);
                preparedStatement.executeUpdate();

                preparedStatement = connection
                        .prepareStatement("ALTER TABLE " + table.tableName + " AUTO_INCREMENT = " + table.initialAutoIncrement);
                preparedStatement.executeUpdate();

            }
            catch (Throwable e)
            {
                handleException(e,"Exception while execute cleanDatabase() for table " + table.tableName);
            }
            finally {
                closeConnection(connection);
            }
        }


    }

    private class Table {
        public String tableName;
        public String primaryKey;
        public int initialAutoIncrement;

        private Table(String tableName, String primaryKey, int initialAutoIncrement) {
            this.tableName = tableName;
            this.primaryKey = primaryKey;
            this.initialAutoIncrement = initialAutoIncrement;
        }
    }

}
