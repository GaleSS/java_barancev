package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static ru.stqa.pft.addressbook.model.ContactData.*;


public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws InterruptedException {
        Contacts before = app.contact().all();
        File photo = new File("src\\test\\resources\\images.jpg");

        ContactData createdContact = new ContactData().withName("test45name").withLastname("test45lastname").withEmail("test45@test.com")
                .withAddress("Test address").withPhoto(photo);

        createdContact.withAllEmails(mergeEmails(createdContact));
        createdContact.withAllPhones(mergePhones(createdContact));

        app.contact().create(createdContact);
        app.goTo().MainPage();

        assertEquals(before.size()+1,app.contact().count());
        Contacts after = app.contact().all();

        assertThat(after,equalTo(
                before.withAdded(createdContact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
    }

}
