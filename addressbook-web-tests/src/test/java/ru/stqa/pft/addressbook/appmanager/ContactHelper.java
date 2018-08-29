package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    private Contacts contactsCache = null;

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void fillAllContactFields(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("address"), contactData.getAddress());
        type(By.name("lastname"), contactData.getLastname());
        attach(By.name("photo"),contactData.getPhoto());

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
        contactsCache = null;
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
            String address = element.findElement(By.xpath("./td[4]")).getText();
            String allEmails = element.findElement(By.xpath("./td[5]")).getText();
            String allPhones = element.findElement(By.xpath("./td[6]")).getText();
            contactsCache.add(new ContactData().withId(id).withLastname(lastName).withName(name).withAddress(address)
                    .withAllEmails(allEmails).withAllPhones(allPhones));
        }
        return contactsCache;
    }

    public int count() {
        return wd.findElements(By.name("entry")).size();
    }

    public ContactData infoFromEditForm(ContactData contact)
    {
        editContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        ContactData infoFromEditForm = new ContactData().withId(contact.getId()).withName(firstname).withLastname(lastname).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone);

        return infoFromEditForm;
    }


    public void addToGroup(GroupData group, ArrayList<ContactData> contacts){

        for (ContactData contact : contacts){
            selectContactById(contact.getId());
        }
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
        wd.findElement(By.name("add")).click();
    }

    public void filterContactsListByGroup(GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
    }

    public void removeFromGroup(GroupData groupWithContactm, ArrayList<ContactData> contactFromGroup) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupWithContactm.getName());
        for (ContactData contact : contactFromGroup){
            selectContactById(contact.getId());
        }
        wd.findElement(By.name("remove")).click();
    }
}


