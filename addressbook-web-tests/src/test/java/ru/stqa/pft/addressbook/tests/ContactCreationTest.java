package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static ru.stqa.pft.addressbook.model.ContactData.*;


public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsJson() {
        List<Object[]> list = new ArrayList<Object[]>();
        String thisLine;
        String json = "";

        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
            while ((thisLine = br.readLine()) != null)
            {
                json += thisLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json,new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map(b -> new Object[]{b}).collect(Collectors.toList()).iterator();
    }


    @Test(dataProvider = "validContactsJson")
    public void testContactCreation(ContactData createdContact) throws SQLException, InterruptedException {
        Thread.sleep(1000);
        Contacts before = app.db().allContacts();
        //File photo = new File("src\\test\\resources\\images.jpg");

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
