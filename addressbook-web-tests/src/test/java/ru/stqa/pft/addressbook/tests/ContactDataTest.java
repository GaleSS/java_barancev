package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static ru.stqa.pft.addressbook.model.ContactData.mergeEmails;
import static ru.stqa.pft.addressbook.model.ContactData.mergePhones;

public class ContactDataTest extends TestBase{

    @BeforeSuite
    public void checkPrecondition()
    {
        app.goTo().MainPage();
        if (app.contact().all().size() == 0)
        {
            app.contact().createContact(new ContactData().withName("testName").withLastname("testLastname").withAddress("Str. asdasdasdasdasd")
            .withHomePhone("+314 213 44").withWorkPhone("456-99-08"));
        }
    }

    @Test
    public void testContactData()
    {
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromEditForm = app.contact().infoFromEditForm(contact);

        infoFromEditForm.withAllEmails(mergeEmails(infoFromEditForm)).withAllPhones(mergePhones(infoFromEditForm));

        Assert.assertEquals(contact,infoFromEditForm);

    }
}
