package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test
    public void testGroupDeletion() {
        app.getContactHelper().editContact();
        app.getContactHelper().fillAllContactFields(new ContactData("testmodified45", "testmodified45", "testmodified45"));
        app.getContactHelper().updateContact();
    }
}
