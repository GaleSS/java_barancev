package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactDataTest extends TestBase{

    @BeforeSuite
    public void checkPrecondition()
    {
        app.goTo().MainPage();
        if (app.contact().all().size() == 0)
        {
            app.contact().createContact(new ContactData().withName("testName").withLastname("testLastname").withEmail("test@test.com")
                    .withAddress("Str. asdasdasdasdasd")
            .withEmail2("test2@test.com").withEmail3("test2@test.com").withEmail3("test3@test.com")
            .withHomePhone("+314 213 44").withWorkPhone("456-99-08"));
        }
    }

    @Test
    public void testContactData()
    {
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromEditForm = app.contact().infoFromEditForm(contact);

        infoFromEditForm.withAllEmails(mergeEmails(infoFromEditForm)).withAllPhones(mergePhones(infoFromEditForm))
                .withEmail(null).withEmail2(null).withEmail3(null)
                .withHomePhone(null).withWorkPhone(null).withMobilePhone(null);


        Assert.assertEquals(contact.getAllEmails(),infoFromEditForm.getAllEmails());
        Assert.assertEquals(contact.getAllPhones(),infoFromEditForm.getAllPhones());
        Assert.assertEquals(contact,infoFromEditForm);

    }

    public static String cleaned(String phone)
    {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

    public String mergeEmails(ContactData contact)
    {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3()).stream().filter(s -> !s.equals("")).collect(Collectors.joining("\n"));
    }

    public String mergePhones(ContactData contact)
    {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone()).stream().filter(s -> !s.equals(""))
                .map(ContactDataTest::cleaned).collect(Collectors.joining("\n"));
    }



}
