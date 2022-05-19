package DB;

import java.sql.SQLException;

public class DeleteLibrarian {
    public void deleteLibrarian(String user_name) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String librarian_sql = "DELETE FROM librarian WHERE `librarian`.`user_name` = '"+user_name+"';";
        databaseConnection.statement.executeUpdate(librarian_sql);
    }
}
