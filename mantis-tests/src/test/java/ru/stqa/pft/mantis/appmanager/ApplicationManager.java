package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.remote.BrowserType.*;

public class ApplicationManager {

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    private final Properties properties;
    private WebDriver wd;
    private String browser;

    public void init() throws IOException {
        //System.setProperty("webdriver.gecko.driver", "E:\\\\Tools\\geckodriver\\geckodriver.exe");
        String target = System.getProperty("target","local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties",target)));
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

    }

    public void stop() {
        wd.quit();
    }


}
