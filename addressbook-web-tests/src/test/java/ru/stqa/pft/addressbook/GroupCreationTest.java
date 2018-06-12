package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.*;


public class GroupCreationTest {
    public static FirefoxDriver wd;

    @BeforeMethod
    public void setUp() {
       System.setProperty("webdriver.gecko.driver", "E:\\\\Tools\\geckodriver\\geckodriver.exe");
       wd = new FirefoxDriver();
       wd.manage().timeouts().implicitlyWait(10, SECONDS);
       wd.get("http://localhost/addressbook/group.php");
       wd.findElement(By.name("user")).click();
       wd.findElement(By.name("user")).clear();
       wd.findElement(By.name("user")).sendKeys("admin");
       wd.findElement(By.name("pass")).click();
       wd.findElement(By.name("pass")).clear();
       wd.findElement(By.name("pass")).sendKeys("secret");
       wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    @Test
    public void testGroupCreation()
    {
        wd.findElement(By.linkText("Групи")).click();
        wd.findElement(By.name("new")).click();
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys("test44");
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys("test44");
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys("test44");
        wd.findElement(By.name("submit")).click();
        wd.findElement(By.linkText("Групи")).click();
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }

    public static boolean isAlertPresent(FirefoxDriver wd)
    {
        try{
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e)
        {
            return false;
        }
    }

}