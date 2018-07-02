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
import java.util.List;

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
        type(By.name("middlename"), contactData.getMiddlename());
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

    public void editContact() {click(By.xpath("/html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img"));}

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
        while (true)
        {
            try {
                index++;
                lastname = wd.findElement(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[2]")).getText();
                name = wd.findElement(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[3]")).getText();
                ContactData contact = new ContactData(name, null, lastname, null);
                contacts.add(contact);
            } catch (NoSuchElementException e) {
                break;
            }

        }
        return contacts;
    }
}

