package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase{

    @BeforeMethod
    public void checkPrecondition ()
    {
        if (! app.contact().isElementPresent(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img")))
        {
            app.contact().createContact(new ContactData().withName("formodify").withEmail("formodify").withLastname("formodify"));
        }
    }

    @Test
    public void testContactModification() {

        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("testmodified45").withEmail("testmodified45").withLastname("testmodified45");

        app.contact().modify(contact);
        app.goTo().MainPage();

        Assert.assertEquals(before.size(), app.contact().count());

        Contacts after = app.contact().all();
        assertThat(after,equalTo(
                before.without(modifiedContact).withAdded(contact)));
    }
}
