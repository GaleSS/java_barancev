package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


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
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        app.goTo().GroupPage();
        assertEquals(before.size()-1,app.group().count());
        Groups after = app.group().all();
        assertThat(after,equalTo(before.without(deletedGroup)));
    }
}
