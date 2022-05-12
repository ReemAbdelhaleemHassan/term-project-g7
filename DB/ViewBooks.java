package DB;

import java.sql.SQLException;

public class ViewBooks {
    public void viewBooks(int cases) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql = null;
        if(cases == 1){
            sql = "select * from available_Books";
        } else if (cases == 2) {
            sql = "select * from borrow_Book";
        }
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(sql);
        while(databaseConnection.resultSet.next()) {
            System.out.println(databaseConnection.resultSet.getString("book_name"));
        }
    }
}
