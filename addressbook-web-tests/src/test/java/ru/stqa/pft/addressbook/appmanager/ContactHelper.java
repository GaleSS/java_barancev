package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase
{
    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillAllContactFields(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
    }

    public void initNewContact() {
        click(By.xpath("/html/body/div/div[3]/ul/li[2]/a"));
    }

    public void selectContact() {click(By.name("selected[]"));}

    public void deleteSelectedContact() {click(By.xpath("/html/body/div[1]/div[4]/form[2]/div[2]/input"));}


    public void confirmDeletionYes() { wd.switchTo().alert().accept(); }
    public void confirmDeletionТщ() { wd.switchTo().alert().dismiss(); }
}

