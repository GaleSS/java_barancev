package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class GroupCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups()
    {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
        list.add(new Object[]{new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
        list.add(new Object[]{new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
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
