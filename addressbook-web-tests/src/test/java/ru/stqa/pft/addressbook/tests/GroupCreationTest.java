package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws InterruptedException {
        app.getNavigationHelper().goToGroupPage();
        int before =  app.getGroupHelper().elementCount(By.name("selected[]"));
        app.getGroupHelper().createGroup(new GroupData("test45", "test45", "test45"));
        app.getNavigationHelper().goToGroupPage();
        int after =  app.getGroupHelper().elementCount(By.name("selected[]"));
        Assert.assertEquals(before, after-1);
    }

}
