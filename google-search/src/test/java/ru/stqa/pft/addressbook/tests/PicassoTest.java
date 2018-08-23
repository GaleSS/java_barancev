package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class PicassoTest extends TestBase{

    @Test
    public void within5Results() {
        boolean within5 = false;
        app.search().openSearchResults("Пикассо");
        ArrayList<String> srchResults = app.search().googleLinksResults();
       for (int i=0;i<4;i++)
       {
           within5 = srchResults.get(i).contains("wikipedia");
           if (within5) break;
       }
        Assert.assertTrue(within5);
    }
}
