package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws InterruptedException {
        List<ContactData> before = app.getContactHelper().getContactList();

        ContactData contact = new ContactData().withName("test45name").withLastname("test45lastname").withEmail("test45@test.com");
        create(contact);
        app.goTo().MainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(), after.size() -1 );

        before.add(contact);
        Comparator <? super ContactData> byAlphabet = (q1, q2) -> (q1.getName()+q1.getLastname()).compareTo(q2.getName()+q2.getLastname());
        before.sort(byAlphabet);
        after.sort(byAlphabet);
        Assert.assertEquals(before, after);

    }

    public void create(ContactData createdContact) {
        app.getContactHelper().initNewContact();
        app.getContactHelper().fillAllContactFields(createdContact,true);
        app.getContactHelper().submitContactCreation();
    }

}
