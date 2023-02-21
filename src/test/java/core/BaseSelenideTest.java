package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.logging.Logger;

abstract public class BaseSelenideTest {

    protected static Logger log = Logger.getLogger(BaseSelenideTest.class.getName());

    @BeforeEach
    public void setUpSelenide() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "2200x1180";
        Configuration.headless = false;
        Configuration.timeout = 4000;
        Configuration.downloadsFolder = "src//test//resources//downloads";
        Configuration.proxyEnabled = true;
    }

    @AfterEach
    public void closeTest() {
        Selenide.closeWebDriver();
    }
}
