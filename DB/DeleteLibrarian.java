package DB;

import java.sql.SQLException;

public class DeleteLibrarian {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    public void deleteLibrarian(String user_name) throws SQLException {

        databaseConnection.connect();
        String librarian_sql = "DELETE FROM librarian WHERE `librarian`.`user_name` = '"+user_name+"';";
        databaseConnection.statement.executeUpdate(librarian_sql);
    }
}
