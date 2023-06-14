package dao;
import java.sql.*;
public class Database {
    public void initiateConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sonoo","root","root");
//here sonoo is database name, root is username and password
            Statement stmt=connection.createStatement();
            connection.close();
        }catch(Exception e){ System.out.println(e);}
    }
}
