package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void selectGroupById(int id) {wd.findElement(By.cssSelector("input[value='"+id+"']")).click();}

    public void createGroup(GroupData groupData) {
        initNewGroup();
        fillAllFields(groupData);
        submitGroupCreation();
        groupCache = null;
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCache = null;
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        modifySelectedGroups();
        fillAllFields(group);
        groupCache = null;
        updateGroup();
    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null)
        {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements)
        {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData().withId(id).withName(name).withHeader(null).withFooter(null);
            groupCache.add(group);
        }
        return new Groups(groupCache);
    }
}
