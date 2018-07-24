package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void fillAllContactFields(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("email"), contactData.getEmail());
        type(By.name("lastname"), contactData.getLastname());

        if (creation && contactData.getGroup() != null) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else if (!creation) {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initNewContact() {
        click(By.xpath("/html/body/div/div[3]/ul/li[2]/a"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("/html/body/div[1]/div[4]/form[2]/div[2]/input"));
    }


    public void confirmDeletionYes() {
        wd.switchTo().alert().accept();
    }

    public void confirmDeletionNo() {
        wd.switchTo().alert().dismiss();
    }

    public void editContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

    }

    public void create(ContactData createdContact) {
        initNewContact();
        fillAllContactFields(createdContact, true);
        submitContactCreation();
    }

    public void delete(ContactData deletedContact) {
        selectContactById(deletedContact.getId());
        deleteSelectedContact();
        confirmDeletionYes();
        contactsCache = null;
    }

    public void modify(ContactData contact) {
        editContactById(contact.getId());
        fillAllContactFields(contact, false);
        updateContact();
        contactsCache = null;
    }

    public void createContact(ContactData contactData) {
        initNewContact();
        fillAllContactFields(contactData, true);
        submitContactCreation();
        contactsCache = null;
    }


    private Contacts contactsCache = null;

    public Contacts all() {
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }

        contactsCache = new Contacts();

        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String name = element.findElement(By.xpath("./td[3]")).getText();
            String email = element.findElement(By.xpath("./td[5]/a")).getText();
            contactsCache.add(new ContactData().withId(id).withLastname(lastName).withName(name).withEmail(email));
        }
        return contactsCache;
    }

    public int count() {
        return wd.findElements(By.name("entry")).size();
    }

}


