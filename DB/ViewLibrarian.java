package DB;
import java.sql.*;

public class ViewLibrarian {
    public ResultSet viewLibrarian() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql = "select * from librarian";
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(sql);

        return databaseConnection.resultSet.getStatement().getResultSet();
    }
}
