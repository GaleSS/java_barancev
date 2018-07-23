package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws InterruptedException {
        Contacts before = app.contact().all();

        ContactData contact = new ContactData().withName("test45name").withLastname("test45lastname").withEmail("test45@test.com");
        app.contact().create(contact);
        app.goTo().MainPage();
        assertEquals(before.size()+1,app.contact().count());
        Contacts after = app.contact().all();

        assertThat(after,equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
    }

}
