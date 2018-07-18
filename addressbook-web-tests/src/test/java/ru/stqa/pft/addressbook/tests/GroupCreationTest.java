package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws InterruptedException {
        app.goTo().GroupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test4").withFooter("test4").withHeader("test4");
        app.group().createGroup(group);
        app.goTo().GroupPage();
        assertEquals(before.size()+1,app.group().count());
        Groups after = app.group().all();
        //before.add(group);
        //Assert.assertEquals(before, after);
        assertThat(after,equalTo(
                before.withAdded(group.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));

    }
}
