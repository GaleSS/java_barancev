package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws InterruptedException {
        app.getContactHelper().initNewContact();
        app.getContactHelper().fillAllContactFields(new ContactData("test45", "test45", "test45"));
        app.getContactHelper().submitContactCreation();
    }

}
