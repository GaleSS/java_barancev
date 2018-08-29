package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class RemoveContactsFromGroupTest extends TestBase {

    private static ContactData contactFromGroup;
    private static GroupData groupWithContact;

    @BeforeMethod
    public void init() throws SQLException {

        if (!app.db().isGroupPresent()){
            app.db().addGroup(new GroupData().withName("AddedGroup").withHeader("AddedGroup").withFooter("AddedGroup"));
            app.goTo().MainPage();
        }

        if (!app.db().isContactPresent())
        {
            app.db().addContact(new ContactData().withName("fordeletion").withEmail("fordeletion").withLastname("fordeletion"));
            app.goTo().MainPage();
        }

        contactFromGroup = app.db().allContacts().iterator().next();
        groupWithContact = app.db().allGroups().iterator().next();

        app.db().addContactToGroup(contactFromGroup,groupWithContact);
    }

    @Test
    public void testRemoveContactsFromGroup() throws SQLException {

        Contacts before = app.db().allContacts(groupWithContact);
        app.goTo().MainPage();
        ArrayList<ContactData> removeContacts = new ArrayList<>();
        removeContacts.add(contactFromGroup);
        app.contact().removeFromGroup(groupWithContact,removeContacts);

        app.goTo().MainPage();
        app.contact().filterContactsListByGroup(groupWithContact);

        assertEquals(before.size()-1,app.contact().count());
        Contacts after = app.contact().all();

        assertThat(after,equalTo(
                before.without(contactFromGroup)));

    }
}
