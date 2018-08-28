package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.model.ContactData.mergeEmails;
import static ru.stqa.pft.addressbook.model.ContactData.mergePhones;

public class ContactModificationTest extends TestBase{

    @BeforeMethod
    /*public void checkPrecondition ()
    {
        if (! app.contact().isElementPresent(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img")))
        {
            app.contact().createContact(new ContactData().withName("formodify").withEmail("formodify").withLastname("formodify"));
        }
    }*/
    public void checkPreconditionViaDB() throws SQLException {
        if (!app.db().isContactPresent())
        {
            app.db().addContact(new ContactData().withName("formodify").withEmail("formodify").withLastname("formodify"));
            app.goTo().MainPage();
        }
    }

    @Test
    public void testContactModification() throws SQLException {

        Contacts before = app.db().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("testmodified45").withEmail("testmodified45").withLastname("testmodified45");
        contact.withAllEmails(mergeEmails(contact));
        contact.withAllPhones(mergePhones(contact));

        app.contact().modify(contact);
        app.goTo().MainPage();

        Assert.assertEquals(before.size(), app.contact().count());

        Contacts after = app.contact().all();
        assertThat(after,equalTo(
                before.without(modifiedContact).withAdded(contact)));
    }
}
