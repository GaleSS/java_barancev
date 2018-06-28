package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isElementPresent(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img")))
        {
            app.getContactHelper().createContact(new ContactData("fordeletion", "fordeletion", "fordeletion", null));
        }
        app.getNavigationHelper().goToMainPage();
        int before =  app.getGroupHelper().elementCount(By.name("selected[]"));
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().confirmDeletionYes();
        app.getNavigationHelper().goToMainPage();
        int after =  app.getGroupHelper().elementCount(By.name("selected[]"));
        Assert.assertEquals(before, after+1);
    }

}
