package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTest extends TestBase
{
    @Test
    public void testGroupModification() throws InterruptedException {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isElementPresent(By.name("selected[]")))
        {
            app.getGroupHelper().createGroup(new GroupData("formodify", "formodify", "formodify"));
        }
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().modifySelectedGroups();
        GroupData group = new GroupData(before.get(before.size()-1).getId(),"test45modified", "test45modified", "test45modified");
        app.getGroupHelper().fillAllFields(group);
        app.getGroupHelper().updateGroup();
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size(), after.size());

        before.remove(before.size()-1);
        before.add(group);
        Assert.assertEquals(new HashSet<Object> (before), new HashSet<Object>(after));
    }

}
