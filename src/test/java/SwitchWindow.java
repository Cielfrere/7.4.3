import com.codeborne.selenide.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class SwitchWindow {

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
    public void switchWindow() {
        Selenide.open("https://the-internet.herokuapp.com/windows");
        $("a[href=\'/windows/new\']").click();
        switchTo().window(1);
        $("h3").shouldHave(Condition.text("New Window"));
        WebDriverRunner.getWebDriver().close();
        switchTo().window(0);
        $("h3").shouldHave(Condition.text("Opening a new window"));
    }
}
