package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws InterruptedException {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();

        GroupData group = new GroupData("test4", "test4", "test4");

        app.getGroupHelper().createGroup(group);
        app.getNavigationHelper().goToGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();


        group.setId(after.stream().max((o1,o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
        before.add(group);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }


}
