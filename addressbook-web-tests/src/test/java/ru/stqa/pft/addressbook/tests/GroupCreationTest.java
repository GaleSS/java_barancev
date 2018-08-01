package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class GroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsXml() {
        List<Object[]> list = new ArrayList<Object[]>();
        String thisLine;
        String xml = "";

        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/groups.xml"))) {
            while ((thisLine = br.readLine()) != null)
            {
                xml += thisLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
        return groups.stream().map(b -> new Object[]{b}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsJson() {
        List<Object[]> list = new ArrayList<Object[]>();
        String thisLine;
        String json = "";

        try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/groups.json"))) {
            while ((thisLine = br.readLine()) != null)
            {
                json += thisLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json,new TypeToken<List<GroupData>>(){}.getType());
        return groups.stream().map(b -> new Object[]{b}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validGroupsJson")
    public void testGroupCreation(GroupData group) throws InterruptedException {
        app.goTo().GroupPage();
        Groups before = app.group().all();
        app.group().createGroup(group);
        app.goTo().GroupPage();
        assertEquals(before.size()+1,app.group().count());
        Groups after = app.group().all();
        //before.add(group);
        //Assert.assertEquals(before, after);
        assertThat(after,equalTo(
                before.withAdded(group.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));

    }
}
