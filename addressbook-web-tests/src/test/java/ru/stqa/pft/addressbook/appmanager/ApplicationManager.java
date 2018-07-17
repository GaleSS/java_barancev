package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.remote.BrowserType.*;

public class ApplicationManager {

    public ApplicationManager (String browser) {
        this.browser = browser;
    }

    private WebDriver wd;
    private String browser;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private GroupHelper groupHelper;

    public void init() {
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
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }


    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }
    public GroupHelper group() {
        return groupHelper;
    }
}
