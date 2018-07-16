package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTest extends TestBase
{
    @BeforeMethod
    public void checkConditions(){
        app.goTo().GroupPage();
        if (app.group().all().size() == 0)
        {
            app.group().createGroup(new GroupData().withName("formodify").withFooter("formodify").withHeader("formodify"));
        }
        app.goTo().GroupPage();
    }

    @Test
    public void testGroupModification() throws InterruptedException {

        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("modifiedGroup").withFooter("modifiedGroup").withHeader("modifiedGroup");

        app.group().modify(group);
        app.goTo().GroupPage();
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(before.size(), after.size());

        before.remove(modifiedGroup);
        before.add(group);

        Assert.assertEquals(before, after);
    }

}
