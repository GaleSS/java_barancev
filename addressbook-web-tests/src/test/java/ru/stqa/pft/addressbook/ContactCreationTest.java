package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws InterruptedException {
        initNewContact();
        fillAllContactFields(new ContactData("test45", "test45", "test45"));
        submitContactCreation();
    }

}
