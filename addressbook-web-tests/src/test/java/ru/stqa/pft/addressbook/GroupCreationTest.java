package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        goToGroupPage();
        initNewGroup();
        fillAllFields(new GroupData("test45", "test45", "test45"));
        submitGroupCreation();
        goToGroupPage();
    }

}
