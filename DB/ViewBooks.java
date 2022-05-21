package DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewBooks {
    public ResultSet viewBooks(int cases, String userName) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql = null;
        if(cases == 1){
            sql = "select * from available_Books";
        } else if (cases == 2) {
            sql = "select * from borrow_Book";
        } else if (cases == 3){
            sql = "select * from borrow_Book natural join user where user_name = '"+userName+"';";
        }
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(sql);

        return databaseConnection.resultSet.getStatement().getResultSet();
    }
}
