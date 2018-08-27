package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.IOException;
import java.sql.SQLException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws IOException, SQLException {
        app.init();
    }

    @AfterSuite
    public void tearDown() throws SQLException {
        app.stop();
    }

}
