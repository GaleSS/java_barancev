package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;


public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    public void checkConditions(){
        app.goTo().GroupPage();
        if (app.group().all().size() == 0)
        {
            app.group().createGroup(new GroupData().withName("fordeletion").withFooter("fordeletion").withHeader("fordeletion"));
        }
        app.goTo().GroupPage();
    }

    @Test
    public void testGroupDeletion(){

        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.groupHelper.delete(deletedGroup);
        app.goTo().GroupPage();
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(before.size()-1,after.size());

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);


    }
}
