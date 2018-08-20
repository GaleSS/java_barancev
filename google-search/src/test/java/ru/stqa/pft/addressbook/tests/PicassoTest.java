package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class PicassoTest extends TestBase{

    @Test
    public void within5Results() {
       ArrayList<WebElement> srchResults = app.search().googleResults("Пикассо");
        System.out.println();
    }

}
