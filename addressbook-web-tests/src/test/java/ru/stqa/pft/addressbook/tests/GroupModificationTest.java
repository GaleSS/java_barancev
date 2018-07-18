package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTest extends TestBase
{
    @BeforeMethod
    public void checkConditions(){
        app.goTo().GroupPage();
        if (app.group().all().size() == 0)
        {
            app.group().createGroup(new GroupData().withName("formodify").withFooter("formodify").withHeader("formodify"));
        }
        app.goTo().GroupPage();
    }

    @Test
    public void testGroupModification() throws InterruptedException {
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("modifiedGroup").withFooter("modifiedGroup").withHeader("modifiedGroup");

        app.group().modify(group);
        app.goTo().GroupPage();
        assertEquals(before.size(), app.group().count());
        Groups after = app.group().all();
        assertThat(after,equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
