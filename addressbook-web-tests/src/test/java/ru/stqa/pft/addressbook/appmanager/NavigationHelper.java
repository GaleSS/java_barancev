package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void GroupPage() {
        if (!(isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText() == "Групи" && isElementPresent(By.name("new")))) {
            click(By.linkText("Групи"));
        }
    }

    public void MainPage() {
        //if (!(isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText() == "Групи" && isElementPresent(By.name("new")))) {
            click(By.linkText("Головна"));
        //}
    }
}
