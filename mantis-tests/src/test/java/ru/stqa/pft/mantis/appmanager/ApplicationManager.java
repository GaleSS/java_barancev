package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.mantis.tests.RegistrationTest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;

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
    private RegistrationHelper registrationHelper;

    public void init() throws IOException {
        //System.setProperty("webdriver.gecko.driver", "E:\\\\Tools\\geckodriver\\geckodriver.exe");
        String target = System.getProperty("target","local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties",target)));
    }

    public void stop() {
        if (wd!=null){wd.quit();}
    }

    public HttpSession newSession()
    {
        return new HttpSession(this);
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }


    public RegistrationHelper registration() {
        if (registrationHelper == null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public WebDriver getDriver() {
        if (wd==null){
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
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
}
