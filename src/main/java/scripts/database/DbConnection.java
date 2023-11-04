package scripts.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private Connection connection;
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;
    public DbConnection(String dbUrl, String dbUsername, String dbPassword)  throws Exception{
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public void closeConnection() throws SQLException{
        connection.close();
    }

    public Connection getOpenConnection() throws SQLException {
        connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        return connection;
    }
}
