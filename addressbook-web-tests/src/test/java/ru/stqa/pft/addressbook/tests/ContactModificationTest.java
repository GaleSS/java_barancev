package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification() {
        app.getContactHelper().editContact();
        app.getContactHelper().fillAllContactFields(new ContactData("testmodified45", "testmodified45", "testmodified45", null), false);
        app.getContactHelper().updateContact();
    }
}
