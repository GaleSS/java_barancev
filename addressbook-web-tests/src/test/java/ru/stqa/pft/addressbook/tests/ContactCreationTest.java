package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws InterruptedException {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initNewContact();
        app.getContactHelper().fillAllContactFields(new ContactData("test45name", "test45@test.com", "test45lastname", null), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().goToMainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(), after.size() -1 );
    }

}
