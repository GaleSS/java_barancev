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

public class ContactDeletionTest extends TestBase {

   /* @BeforeMethod
    public void checkPreconditionViaGui()
    {
        if (! app.contact().isElementPresent(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img")))
        {
            app.contact().createContact(new ContactData().withName("fordeletion").withEmail("fordeletion").withLastname("fordeletion"));
        }
        app.goTo().MainPage();
    }*/

    @BeforeMethod
    public void checkPreconditionViaDB() throws SQLException {
        if (!app.db().isContactPresent())
        {
            app.db().addContact(new ContactData().withName("fordeletion").withEmail("fordeletion").withLastname("fordeletion"));
            app.goTo().MainPage();
        }
    }

    @Test
    public void testContactDeletion() throws InterruptedException, SQLException {

        Contacts before = app.db().all();
        ContactData deletedContact = before.iterator().next();

        app.contact().delete(deletedContact);
        Thread.sleep(1000);
        app.goTo().MainPage();


        Assert.assertEquals(before.size(), app.contact().count()+1);
        Contacts after = app.contact().all();

        assertThat(after,equalTo(
                before.without(deletedContact)));
    }

}
