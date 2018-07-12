package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void checkPrecondition()
    {
        if (! app.getContactHelper().isElementPresent(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img")))
        {
            app.getContactHelper().createContact(new ContactData("fordeletion", "fordeletion", "fordeletion", null));
        }
        app.goTo().MainPage();
    }

    @Test
    public void testContactDeletion() throws InterruptedException {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;

        app.getContactHelper().selectContact(index);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().confirmDeletionYes();
        Thread.sleep(1000);
        app.goTo().MainPage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(), after.size()+1);

        before.remove(index);
        Comparator<? super ContactData> byAlphabet = (q1, q2) -> (q1.getName()+q1.getLastname()).compareTo(q2.getName()+q2.getLastname());
        before.sort(byAlphabet);
        after.sort(byAlphabet);
        Assert.assertEquals(before, after);
    }

}
