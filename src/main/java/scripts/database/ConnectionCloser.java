package scripts.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionCloser {
    public void close(DbConnection connection, ResultSet rs, Statement st){
        closeConnection(connection);
        closeResultSet(rs);
        closeStatement(st);
    }

    public void close(DbConnection connection, ResultSet rs){
        closeConnection(connection);
        closeResultSet(rs);
    }

    public void close(DbConnection connection, Statement st){
        closeConnection(connection);
        closeStatement(st);
    }

    private void closeConnection(DbConnection connection){
        try {
            if (connection != null) {
                connection.closeConnection();
            }
        } catch (SQLException ex){
            System.out.println("Can not close connection");
        }
    }

    private void closeResultSet(ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex){
            System.out.println("Can not close resultSet");
        }
    }

    private void closeStatement(Statement st){
        try {
            if (st != null){
                st.close();
            }
        } catch (SQLException ex){
            System.out.println("Can not close ResultSet");
        }
    }
}
