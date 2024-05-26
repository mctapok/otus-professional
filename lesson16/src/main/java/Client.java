import java.sql.SQLException;

public class Client {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Transaction transaction = DbTransaction.create();
        transaction.saveUser("bob", "bob");
    }
}
