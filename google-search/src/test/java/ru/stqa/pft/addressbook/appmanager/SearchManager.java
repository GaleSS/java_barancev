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

    public ArrayList<String> googleLinksResults(String searchString){
        wd.get("https://www.google.com/");
        type(By.id("lst-ib"),searchString);
        wd.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
        ArrayList<String> googleResults = new ArrayList<>();
        for (WebElement element : wd.findElements(By.xpath("//cite")))
        {
            googleResults.add(element.getText());
        }
        return googleResults;
    }
}
