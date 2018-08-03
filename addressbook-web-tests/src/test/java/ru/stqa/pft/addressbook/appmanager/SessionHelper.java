package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase{


    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    protected void login(String username, String password, String url) {
        wd.get(url);//"http://localhost/addressbook/"
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
