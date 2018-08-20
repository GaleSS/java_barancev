package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class SearchManager extends HelperBase{

    public SearchManager(WebDriver wd) {
        super(wd);
    }

    public ArrayList<WebElement> googleResults(String searchString){
        wd.get("https://www.google.com/");
        type(By.id("lst-ib"),searchString);
        wd.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
        return (ArrayList<WebElement>) wd.findElements(By.xpath("//descendant::[@id=rso]/descendant::cite"));
    }
}
