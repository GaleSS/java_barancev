package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
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
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().modifySelectedGroups();
        app.getGroupHelper().fillAllFields(new GroupData("test45modified", "test45modified", "test45modified"));
        app.getGroupHelper().updateGroup();
        app.getNavigationHelper().goToGroupPage();
    }

}
