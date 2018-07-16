package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;


public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws InterruptedException {
        app.goTo().GroupPage();
        Set<GroupData> before = app.groupHelper.group(app).all();
        GroupData group = new GroupData().withName("test4").withFooter("test4").withHeader("test4");
        app.groupHelper.group(app).createGroup(group);
        app.goTo().GroupPage();
        Set<GroupData> after = app.groupHelper.group(app).all();
        Assert.assertEquals(before.size()+1,after.size());
        group.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);

    }


}
