package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;


import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
