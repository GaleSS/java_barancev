package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isElementPresent(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img")))
        {
            app.getContactHelper().createContact(new ContactData("fordeletion", "fordeletion", "fordeletion", null));
        }
        app.getNavigationHelper().goToMainPage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().confirmDeletionYes();
        app.getNavigationHelper().goToMainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(), after.size()+1);
    }

}
