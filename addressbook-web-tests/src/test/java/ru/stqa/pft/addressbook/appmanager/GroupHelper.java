package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper  extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void updateGroup() {
        click(By.name("update"));
    }


    public void fillAllFields(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initNewGroup() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void modifySelectedGroups() {
        click(By.name("edit"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void createGroup(GroupData groupData) {
        initNewGroup();
        fillAllFields(groupData);
        submitGroupCreation();
    }
}
