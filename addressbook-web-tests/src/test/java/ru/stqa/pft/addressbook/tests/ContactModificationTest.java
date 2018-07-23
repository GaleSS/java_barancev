package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase{

    @BeforeMethod
    public void checkPrecondition ()
    {
        if (! app.getContactHelper().isElementPresent(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img")))
        {
            app.getContactHelper().createContact(new ContactData().withName("formodify").withEmail("formodify").withLastname("formodify"));
        }
    }

    @Test
    public void testContactModification() {

        List<ContactData> before = app.getContactHelper().getContactList();

        ContactData modifiedContact = new ContactData().withName("testmodified45").withEmail("testmodified45").withLastname("testmodified45");
        app.getContactHelper().editContact(before.size()-1);
        app.getContactHelper().fillAllContactFields(modifiedContact, false);
        app.getContactHelper().updateContact();
        app.goTo().MainPage();

        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(before.size()-1);
        before.add(modifiedContact);

        Assert.assertEquals(before.size(),after.size());

        Comparator<? super ContactData> byAlphabet = (q1, q2) -> (q1.getName()+q1.getLastname()).compareTo(q2.getName()+q2.getLastname());
        before.sort(byAlphabet);
        after.sort(byAlphabet);
        Assert.assertEquals(before, after);
    }
}
