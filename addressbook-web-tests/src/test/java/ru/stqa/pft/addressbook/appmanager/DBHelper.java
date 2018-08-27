package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DBHelper {

    private Connection conn;
    private Statement st;

    public DBHelper(Connection conn) throws SQLException {
        this.conn = conn;
        this.st = conn.createStatement();
    }

    public boolean isContactPresent() throws SQLException {
        boolean isPresent = false;

        ResultSet contacts = st.executeQuery("select * from addressbook where deprecated = \"0000-00-00 00:00:00\"");
        if (contacts.next()) {
            isPresent = true;
        }
        contacts.close();
        return isPresent;
    }

    public void addContact(ContactData contactData) throws SQLException {
        ResultSet rs = st.executeQuery("select max(id) from addressbook");
        rs.next();
        int maxId = rs.getInt("max(id)");
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String deprecatedTime = sdf.format(new java.util.Date());
        st.execute("INSERT INTO addressbook (domain_id,id,firstname,lastname,email,email2,email3,home,work,mobile,address,deprecated)\n" +
                String.format("VALUES (0,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"0000-00-00 00:00:00\")"
                        ,maxId+1,contactData.getName(),contactData.getLastname()
                        ,contactData.getEmail(),contactData.getEmail2(),contactData.getEmail3()
                        ,contactData.getHomePhone(),contactData.getWorkPhone(),contactData.getMobilePhone()
                        ,contactData.getAddress()));
    }

    /*public boolean isGroupPresent(){

    }*/
}
