package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void checkConditions(){
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isElementPresent(By.name("selected[]")))
        {
            app.getGroupHelper().createGroup(new GroupData("fordeleteion", "fordeleteion", "fordeleteion"));
        }
        app.getNavigationHelper().goToGroupPage();
    }

    @Test
    public void testGroupDeletion(){

        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroups();
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();

        before.remove(before.size() - 1);
        for (int i=0; i < after.size();i++)
        {
            Assert.assertEquals(before.get(i), after.get(i));
        }

    }


}
