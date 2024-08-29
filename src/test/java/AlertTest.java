import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class AlertTest {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @AfterAll
    public static void tearDown() {
        closeWebDriver();
    }

    @Test
    public void alertTest() {
        Selenide.open("http://the-internet.herokuapp.com/javascript_alerts");

        $("button[onclick='jsAlert()']").click();
        String jsAlert = switchTo().alert().getText();
        Assertions.assertEquals("I am a JS Alert", jsAlert);
        switchTo().alert().accept();

        $("button[onclick='jsConfirm()']").click();
        switchTo().alert().dismiss();
        $("h3").shouldHave(Condition.text("JavaScript Alerts"));

        $("button[onclick='jsPrompt()']").click();
        switchTo().alert().sendKeys("Hello World");
        switchTo().alert().accept();
        $("#result").shouldHave(Condition.text("Hello World"));
    }
}
