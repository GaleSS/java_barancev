package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        ArrayList<ContactData> createdContacts = new ArrayList<>();

        for (int i = 0; i < 2;i++) {
            ContactData contact = new ContactData().withLastname("lastName"+i).withName("name"+i).withAddress("address"+i)
                    .withEmail("email"+i).withEmail2("email"+i).withEmail3("email"+i)
                    .withHomePhone("434"+i).withWorkPhone("0987"+i).withMobilePhone("2323"+i);
            contact.withAllPhones(ContactData.mergePhones(contact)).withAllEmails(ContactData.mergeEmails(contact));

            app.contact().create(contact);
            createdContacts.add(contact.withId(app.db().getMaxContactId()));
        }

        app.goTo().MainPage();
        app.contact().addToGroup(groupForAdding, createdContacts);
        app.goTo().MainPage();

        app.contact().filterContactsListByGroup(groupForAdding);

        assertEquals(before.size()+2,app.contact().count());
        Contacts after = app.contact().all();

        assertThat(after,equalTo(
                before.withAdded(createdContacts.get(0)).withAdded(createdContacts.get(1))));



    }

}
