package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void GroupPage() {
        //if (!(isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText() == "Групи" && isElementPresent(By.name("new")))) {
          //  click(By.linkText("Групи"));
        click(By.xpath("/html/body/div/div[3]/ul/li[3]/a"));
       //}
    }

    public void MainPage() {
        //if (!(isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText() == "Групи" && isElementPresent(By.name("new")))) {
            click(By.xpath("/html/body/div/div[3]/ul/li[1]/a"));
        //}
    }
}
