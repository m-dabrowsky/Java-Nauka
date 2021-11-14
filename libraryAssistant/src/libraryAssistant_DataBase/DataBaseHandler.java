package libraryAssistant_DataBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jdk.nashorn.internal.ir.Statement;

/**
 * Created by Michał Dąbrowski
 */
public class DataBaseHandler {

    private Connection databaseHandler;

    public Connection getConnection(){
        String datebaseName = "library_db";
        String datebaseTitle = "demo";
        String datebaseAuthor = "Michal";
        String databasePublishing = "WZIP";
        String DB_URL = "jdbc:mysql://localhost/3306";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseHandler = DriverManager.getConnection(DB_URL, datebaseTitle, datebaseAuthor);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return databaseHandler;


    }





}
