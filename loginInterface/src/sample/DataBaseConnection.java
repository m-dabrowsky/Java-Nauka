package sample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ExecutionException;

public class DataBaseConnection {


    public Connection databaseLink;


    // łączenie bazy danych z javą
    public Connection getConnection(){
        String driverName = "com.mysql.jdbc.Driver";            // sterownik
        String databaseName = "demo-db";                        // nazwa bazy
        String databaseUser = "root";                           // nazwa użytkownika bazy danych
        String databasePassword = "2353062Koluwb";              // hasło bazy danych
        String url = "jdbc:mysql://localhost:4000/" + databaseName;

        try {
            // załadowanie sterownika
            Class.forName(driverName);
            // uzyskanie połączenia z bazą o podanym url. DriverManager przegląda listę sterowników i wybiera ten
            // który może się połączyć z podana bazą.
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);


        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;







    }

}
