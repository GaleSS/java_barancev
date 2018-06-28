package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import org.testng.annotations.Test;

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
        int before =  app.getGroupHelper().elementCount(By.name("selected[]"));
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().modifySelectedGroups();
        app.getGroupHelper().fillAllFields(new GroupData("test45modified", "test45modified", "test45modified"));
        app.getGroupHelper().updateGroup();
        app.getNavigationHelper().goToGroupPage();
        int after =  app.getGroupHelper().elementCount(By.name("selected[]"));
        Assert.assertEquals(before, after);
    }

}
