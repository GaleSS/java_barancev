package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isElementPresent(By.name("selected[]")))
        {
            app.getGroupHelper().createGroup(new GroupData("fordeleteion", "fordeleteion", "fordeleteion"));
        }
        app.getNavigationHelper().goToGroupPage();
        int before =  app.getGroupHelper().elementCount(By.name("selected[]"));
        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getNavigationHelper().goToGroupPage();
        int after =  app.getGroupHelper().elementCount(By.name("selected[]"));
        Assert.assertEquals(before, after + 1);
    }


}
