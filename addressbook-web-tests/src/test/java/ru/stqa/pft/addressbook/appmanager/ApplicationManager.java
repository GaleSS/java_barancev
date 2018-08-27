package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    private Connection conn;
    private String browser;
    private NavigationHelper navigationHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private GroupHelper groupHelper;
    private DBHelper dBHelper;

    public void init() throws IOException, SQLException {
        //System.setProperty("webdriver.gecko.driver", "E:\\\\Tools\\geckodriver\\geckodriver.exe");
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
        if (browser.equals(FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(EDGE)) {
            wd = new EdgeDriver();
        }

        try {
            conn =
                  DriverManager.getConnection(properties.getProperty("db.host") +
                            String.format("?user=%s&password=%s", properties.getProperty("db.user"), properties.getProperty("db.pass")+"&useLegacyDatetimeCode=false&serverTimezone=UTC&zeroDateTimeBehavior=CONVERT_TO_NULL"));

            //conn =
                  //DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&pass=");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }


        wd.manage().timeouts().implicitlyWait(10, SECONDS);
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        dBHelper = new DBHelper(conn);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"), properties.getProperty("web.baseUrl"));
    }

    public void stop() throws SQLException {
        wd.quit();
        conn.close();
    }


    public ContactHelper contact() {
        return contactHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public GroupHelper group() {
        return groupHelper;
    }
    public DBHelper db() {
        return dBHelper;
    }
}
