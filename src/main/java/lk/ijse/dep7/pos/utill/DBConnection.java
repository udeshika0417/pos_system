package lk.ijse.dep7.pos.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection("jdbc:mysql://localhost:3306/mypos","root","Udeshika1996");

    }
}
