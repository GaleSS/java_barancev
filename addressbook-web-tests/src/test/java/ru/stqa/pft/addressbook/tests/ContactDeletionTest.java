package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void checkPrecondition()
    {
        if (! app.contact().isElementPresent(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img")))
        {
            app.contact().createContact(new ContactData().withName("fordeletion").withEmail("fordeletion").withLastname("fordeletion"));
        }
        app.goTo().MainPage();
    }

    @Test
    public void testContactDeletion() throws InterruptedException {

        Contacts before = app.contact().all();
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
