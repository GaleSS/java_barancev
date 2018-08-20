package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.remote.BrowserType.*;

public class ApplicationManager {

    public ApplicationManager (String browser) {
        this.browser = browser;
    }

    private WebDriver wd;
    private String browser;
    private SearchManager searchManager;


    public void init() throws IOException {
        //System.setProperty("webdriver.gecko.driver", "E:\\\\Tools\\geckodriver\\geckodriver.exe");

        if (browser.equals(FIREFOX))
        {
            wd = new FirefoxDriver();
        } else if (browser.equals(CHROME))
        {
            wd = new ChromeDriver();
        } else if (browser.equals(EDGE))
        {
            wd = new EdgeDriver();
        }

        wd.manage().timeouts().implicitlyWait(10, SECONDS);

        searchManager = new SearchManager(wd);

    }

    public SearchManager search() {
        return searchManager;
    }

    public void stop() {
        wd.quit();
    }

}
