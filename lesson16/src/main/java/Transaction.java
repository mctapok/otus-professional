import java.sql.SQLException;

public interface Transaction {
     void saveUser(String login, String password) throws SQLException;
}
