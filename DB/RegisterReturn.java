package DB;

import java.sql.SQLException;

public class RegisterReturn {
    public void registerReturn(int user_id, int book_id) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql=null;
        String increment = null;

            //register return book
            //sql = "INSERT INTO available_books  ";
            //incerement quantity of this book
           // increment = ""

        //databaseConnection.statement.executeUpdate(sql);
    }
}
