package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        if (! app.getContactHelper().isElementPresent(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img")))
        {
            app.getContactHelper().createContact(new ContactData("fordeletion", "fordeletion", "fordeletion", "test45modified"));
        }
        app.getNavigationHelper().goToMainPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().confirmDeletionYes();
    }

}
