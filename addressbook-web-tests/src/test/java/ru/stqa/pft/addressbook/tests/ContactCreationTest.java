package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws InterruptedException {
        int before =  app.getGroupHelper().elementCount(By.name("selected[]"));
        app.getContactHelper().initNewContact();
        app.getContactHelper().fillAllContactFields(new ContactData("test45", "test45", "test45", null), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToMainPage();
        int after =  app.getGroupHelper().elementCount(By.name("selected[]"));
        Assert.assertEquals(before, after -1 );
    }

}
