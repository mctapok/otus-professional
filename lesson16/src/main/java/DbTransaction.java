import java.sql.*;

public final class DbTransaction implements Transaction {
    private final Connection connection;
    static final String JDBC_URL = "jdbc:h2:~/lesson";

    private DbTransaction() throws SQLException {
        this.connection = DriverManager.getConnection(JDBC_URL, "sa", "");
    }

    public static Transaction create() throws SQLException {
        return new ProxyDbTransaction();
    }

    @Override
    public void saveUser(String login, String password) {
        try(PreparedStatement ps = connection.prepareStatement("insert into users (login, password) values (?, ?);")) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final class ProxyDbTransaction implements Transaction {
        private final DbTransaction dbTransaction;

        private ProxyDbTransaction() throws SQLException {
            this.dbTransaction = new DbTransaction();
        }

        @Override
        public void saveUser(String login, String password) {
            try (Connection conn = dbTransaction.connection;
                 PreparedStatement ps = dbTransaction.connection.prepareStatement("select login from users where login =?;"))
            {
                conn.setAutoCommit(false);

                ps.setString(1,login);
                ResultSet rs = ps.executeQuery();
                dbTransaction.saveUser(login, password);
                if(rs.next()){
                    conn.rollback();
                    System.out.println("login already exists");
                }
                conn.commit();
            }catch (SQLException e){
                try {
                    dbTransaction.connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }finally{
                closeConnection(dbTransaction.connection);
            }
        }
    }

    private static void closeConnection(Connection connection) {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
