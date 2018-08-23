package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private Connection conn;

    public Connection connection(String host, String user, String pass)
    {
        if (conn == null) {
            try {
                conn =
                        DriverManager.getConnection(host +
                                String.format("user=%s&password=%s", user, pass));


            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return conn;
    }
}
