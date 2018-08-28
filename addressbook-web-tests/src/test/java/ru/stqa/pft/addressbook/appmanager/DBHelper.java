package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        st.execute("INSERT INTO addressbook (domain_id,id,firstname,lastname,email,email2,email3,home,work,mobile,address,deprecated)\n" +
                String.format("VALUES (0,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"0000-00-00 00:00:00\")"
                        ,maxId+1,contactData.getName(),contactData.getLastname()
                        ,contactData.getEmail(),contactData.getEmail2(),contactData.getEmail3()
                        ,contactData.getHomePhone(),contactData.getWorkPhone(),contactData.getMobilePhone()
                        ,contactData.getAddress()));
    }

    public Contacts all() throws SQLException {

        Contacts contacts = new Contacts();
        ResultSet resultSet = st.executeQuery("select * from addressbook where deprecated = \"0000-00-00 00:00:00\"");

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String lastName = resultSet.getString("lastname");
            String name = resultSet.getString("firstname");
            String address = resultSet.getString("address");
            String email = resultSet.getString("email");
            String email2 = resultSet.getString("email2");
            String email3 = resultSet.getString("email3");
            String mobile = resultSet.getString("mobile");
            String work = resultSet.getString("work");
            String home = resultSet.getString("home");
            ContactData currentContact = new ContactData().withId(id).withLastname(lastName).withName(name).withAddress(address)
                    .withEmail(email).withEmail2(email2).withEmail3(email3)
                    .withHomePhone(home).withWorkPhone(work).withMobilePhone(mobile);
            currentContact.withAllPhones(ContactData.mergePhones(currentContact)).withAllEmails(ContactData.mergeEmails(currentContact));

            contacts.add(currentContact);
        }
        return contacts;
    }

    /*public boolean isGroupPresent(){

    }*/
}
