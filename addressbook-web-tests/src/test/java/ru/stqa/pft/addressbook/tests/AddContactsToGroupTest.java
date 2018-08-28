package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.sql.SQLException;

public class AddContactsToGroupTest extends TestBase{

    @BeforeMethod
    public void groupAbilityCheck() throws SQLException {
        if (!app.db().isGroupPresent()){
            app.db().addGroup(new GroupData().withName("AddedGroup").withHeader("AddedGroup").withFooter("AddedGroup"));
            app.goTo().MainPage();
        }
    }



    @Test
    public void testAddcontactsToGroup() throws SQLException {

        GroupData groupForAdding = app.db().allGroups().iterator().next();
        Contacts before = app.db().allContacts(groupForAdding);

        ContactData contact1 = new ContactData().withLastname("lastName1").withName("name1").withAddress("address1")
                .withEmail("email11").withEmail2("email12").withEmail3("email13")
                .withHomePhone("434").withWorkPhone("0987").withMobilePhone("2323");
        contact1.withAllPhones(ContactData.mergePhones(contact1)).withAllEmails(ContactData.mergeEmails(contact1));

        ContactData contact2 = new ContactData().withLastname("lastName2").withName("name2").withAddress("address2")
                .withEmail("email21").withEmail2("email22").withEmail3("email23")
                .withHomePhone("2434").withWorkPhone("20987").withMobilePhone("22323");
        contact1.withAllPhones(ContactData.mergePhones(contact1)).withAllEmails(ContactData.mergeEmails(contact1));

        app.contact().create(contact1);
        app.contact().create(contact2);

        app.contact().addToGroup(contact1,contact2);


    }

}
