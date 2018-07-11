package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
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
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isElementPresent(By.name("selected[]")))
        {
            app.getGroupHelper().createGroup(new GroupData("formodify", "formodify", "formodify"));
        }
        app.getNavigationHelper().goToGroupPage();
    }

    @Test
    public void testGroupModification() throws InterruptedException {

        List<GroupData> before = app.getGroupHelper().getGroupList();
        int index = before.size() - 1;
        GroupData group = new GroupData(before.get(index).getId(),"test45modified", "test45modified", "test45modified");

        app.getGroupHelper().modifyGroup(index, group);

        app.getNavigationHelper().goToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(before.size(), after.size());

        before.remove(index);
        before.add(group);
        Assert.assertEquals(new HashSet<Object> (before), new HashSet<Object>(after));
    }

}
