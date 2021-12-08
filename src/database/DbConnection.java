package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public Connection getDbConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/sms_javaswing","root","");
            System.out.println("connection");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
