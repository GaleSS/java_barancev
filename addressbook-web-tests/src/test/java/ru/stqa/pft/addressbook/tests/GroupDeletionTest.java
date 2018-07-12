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
        app.goTo().GroupPage();
        if (app.group().list().size() == 0)
        {
            app.group().createGroup(new GroupData().withName("fordeletion").withFooter("fordeletion").withHeader("fordeletion"));
        }
        app.goTo().GroupPage();
    }

    @Test
    public void testGroupDeletion(){

        List<GroupData> before = app.group().list();
        int index = before.size() - 1;

        app.group().selectGroup(index);
        app.group().deleteSelectedGroups();
        app.goTo().GroupPage();
        List<GroupData> after = app.group().list();

        before.remove(index);
        Assert.assertEquals(before, after);


    }


}
