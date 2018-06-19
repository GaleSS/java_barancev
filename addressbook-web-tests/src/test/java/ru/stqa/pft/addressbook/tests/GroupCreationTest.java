package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() throws InterruptedException {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().initNewGroup();
        app.getGroupHelper().fillAllFields(new GroupData("test45", "test45", "test45"));
        app.getGroupHelper().submitGroupCreation();
        app.getNavigationHelper().goToGroupPage();
    }

}
