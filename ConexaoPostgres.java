import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoPostgres {

 private static final String URL = "jdbc:postgresql://localhost:5432/CRUD";
 private static final String USER = "admin";
 private static final String PASSWORD = "123";

public static Connection conectar() throws SQLException{
return DriverManager.getConnection(URL, USER, PASSWORD);
}

}