package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isElementPresent(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img")))
        {
            app.getContactHelper().createContact(new ContactData("fordeletion", "fordeletion", "fordeletion", null));
        }
        int before =  app.getGroupHelper().elementCount(By.name("selected[]"));
        app.getContactHelper().editContact();
        app.getContactHelper().fillAllContactFields(new ContactData("testmodified45", "testmodified45", "testmodified45", null), false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToMainPage();
        int after =  app.getGroupHelper().elementCount(By.name("selected[]"));
        Assert.assertEquals(before,after);
    }
}
