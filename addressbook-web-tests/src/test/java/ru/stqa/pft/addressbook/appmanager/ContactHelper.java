package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase
{
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {click(By.name("submit")); }
    public void updateContact() {
        click(By.name("update"));
    }

    public void fillAllContactFields(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("email"), contactData.getEmail());
        type(By.name("lastname"), contactData.getLastname());

        if (creation && contactData.getGroup() != null)
        {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else if (!creation)
        {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initNewContact() {
        click(By.xpath("/html/body/div/div[3]/ul/li[2]/a"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {click(By.xpath("/html/body/div[1]/div[4]/form[2]/div[2]/input"));}


    public void confirmDeletionYes() { wd.switchTo().alert().accept(); }
    public void confirmDeletionNo() { wd.switchTo().alert().dismiss(); }

    public void editContact(int index) {
        index = index + 2;
        click(By.xpath("/html/body/div[1]/div[4]/form[2]/table/tbody/tr["+index+"]/td[8]/a/img"));
    }

    public void createContact(ContactData contactData) {
        initNewContact();
        fillAllContactFields(contactData, true);
        submitContactCreation();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        int index = 1;
        String name;
        String lastname;
        String email;
        while (true)
        {
            try {
                index++;
                lastname = wd.findElement(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[2]")).getText();
                name = wd.findElement(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[3]")).getText();
                email = wd.findElement(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[5]/a")).getText();;
                ContactData contact = new ContactData().withName(name).withEmail(email).withLastname(lastname);
                contacts.add(contact);
            } catch (NoSuchElementException e) {
                break;
            }

        }
        return contacts;
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();

        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements)
        {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String name = element.findElement(By.xpath("./td[3]")).getText();
            String email = element.findElement(By.xpath("./td[5]/a")).getText();
            contacts.add(new ContactData().withId(id).withLastname(lastName).withName(name).withEmail(email));
        }

        return contacts;
    }

    public void all_test() {
        Set<ContactData> contacts = new HashSet<ContactData>();


    }
}


