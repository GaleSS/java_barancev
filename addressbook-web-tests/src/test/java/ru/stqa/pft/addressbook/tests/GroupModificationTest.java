package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTest extends TestBase
{
    @BeforeMethod
    public void checkConditions(){
        app.goTo().GroupPage();
        if (app.group().list().size() == 0)
        {
            app.group().createGroup(new GroupData().withName("formodify").withFooter("formodify").withHeader("formodify"));
        }
        app.goTo().GroupPage();
    }

    @Test
    public void testGroupModification() throws InterruptedException {

        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData().withId(before.get(index).getId()).withName("test45modified").withFooter("test45modified").withHeader("test45modified");

        app.group().modify(index, group);

        app.goTo().GroupPage();
        List<GroupData> after = app.group().list();
        Assert.assertEquals(before.size(), after.size());

        before.remove(index);
        before.add(group);
        Assert.assertEquals(new HashSet<Object> (before), new HashSet<Object>(after));
    }

}
