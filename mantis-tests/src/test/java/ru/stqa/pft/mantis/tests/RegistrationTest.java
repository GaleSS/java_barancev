package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

public class RegistrationTest extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test

    public void testRegistration() throws IOException {
        String email = "user2@localhost.localdomain";
        String user = "user2";
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, "password");
        Assert.assertTrue(app.newSession().login(user, "password"));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email){
        MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
