package ru.stqa.pft.addressbook.appmanager;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        int maxId = getMaxContactId();
        st.execute("INSERT INTO addressbook (domain_id,id,firstname,lastname,email,email2,email3,home,work,mobile,address,deprecated)\n" +
                String.format("VALUES (0,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"0000-00-00 00:00:00\")"
                        ,maxId+1,contactData.getName(),contactData.getLastname()
                        ,contactData.getEmail(),contactData.getEmail2(),contactData.getEmail3()
                        ,contactData.getHomePhone(),contactData.getWorkPhone(),contactData.getMobilePhone()
                        ,contactData.getAddress()));
    }

    public int getMaxContactId() throws SQLException {
        ResultSet rs = st.executeQuery("select max(id) from addressbook");
        rs.next();
        return rs.getInt("max(id)");
    }

    public Contacts allContacts() throws SQLException {

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

    public Contacts allContacts(GroupData group) throws SQLException {

        Contacts contacts = new Contacts();
        ResultSet resultSet = st.executeQuery("select * from addressbook\n" +
                "join address_in_groups\n" +
                "on addressbook.id = address_in_groups.id\n" +
                "where addressbook.deprecated = \"0000-00-00 00:00:00\" and address_in_groups.deprecated = \"0000-00-00 00:00:00\" "+
                String.format("and group_id = %s",group.getId()));
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

    public boolean isGroupPresent() throws SQLException {
        boolean isPresent = false;

        ResultSet groups = st.executeQuery("select * from group_list where deprecated = \"0000-00-00 00:00:00\"");
        if (groups.next()) {
            isPresent = true;
        }
        groups.close();
        return isPresent;
    }

    public void addGroup(GroupData groupData) throws SQLException {
        int maxId = getMaxGroupId();
        st.execute("INSERT INTO group_list (domain_id,group_id,group_name,group_header, group_footer, deprecated)\n" +
                String.format("VALUES (0,\"%s\",\"%s\",\"%s\",\"%s\",\"0000-00-00 00:00:00\")"
                        ,maxId+1,groupData.getName(),groupData.getHeader(),groupData.getFooter()));
    }

    private int getMaxGroupId() throws SQLException {
        ResultSet rs = st.executeQuery("select max(group_id) from group_list");
        rs.next();
        return rs.getInt("max(group_id)");
    }

    public Groups allGroups() throws SQLException {
        Groups groups = new Groups();
        ResultSet resultSet = st.executeQuery("select * from group_list where deprecated = \"0000-00-00 00:00:00\"");

        while (resultSet.next()){
            int id = resultSet.getInt("group_id");
            String name = resultSet.getString("group_name");
            String header = resultSet.getString("group_header");
            String footer = resultSet.getString("group_footer");
            GroupData currentGroup = new GroupData().withId(id).withName(name).withHeader(header).withFooter(footer);

            groups.add(currentGroup);
        }
        return groups;
    }

    public void addContactToGroup(ContactData contactFromGroup, GroupData groupWithContact) throws SQLException {
        st.execute("INSERT INTO address_in_groups (domain_id,id,group_id,deprecated)\n" +
                String.format("VALUES (0,\"%s\",\"%s\",\"0000-00-00 00:00:00\")"
                        ,contactFromGroup.getId(),groupWithContact.getId()));
    }
}
