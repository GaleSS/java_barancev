package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTest extends TestBase {

    @Test (enabled = false)
    public void testContactCreation() throws InterruptedException {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initNewContact();
        app.getContactHelper().fillAllContactFields(new ContactData("test45name", "test45@test.com", "test45lastname", null), true);
        app.getContactHelper().submitContactCreation();
        app.goTo().MainPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(), after.size() -1 );

        before.add(new ContactData("test45name", "test45@test.com", "test45lastname", null));
        Comparator <? super ContactData> byAlphabet = (q1, q2) -> (q1.getName()+q1.getLastname()).compareTo(q2.getName()+q2.getLastname());
        before.sort(byAlphabet);
        after.sort(byAlphabet);
        Assert.assertEquals(before, after);

    }

}
