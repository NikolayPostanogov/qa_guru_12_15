package guru.qa.testBase;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.config.Browser;
import guru.qa.config.CredentialsConfig;
import guru.qa.config.WebDriverConfig;
import guru.qa.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    private static final WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.holdBrowserOpen = false;

        if (webDriverConfig.getBrowser().equals(Browser.CHROME)) {
            Configuration.browser = "CHROME";
        } else if (webDriverConfig.getBrowser().equals(Browser.FIREFOX)) {
            Configuration.browser = "FIREFOX";
        } else {
            throw new RuntimeException("No such browser");
        }

        Configuration.baseUrl = webDriverConfig.getBaseUrl();
        Configuration.browserSize = webDriverConfig.getBrowserSize();
        Configuration.browserVersion = webDriverConfig.getBrowserVersion();

        if (webDriverConfig.getEngine().equals("remote")) {
            CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);
            String login = credentialsConfig.login();
            String password = credentialsConfig.password();
            String url = webDriverConfig.getRemoteUrl();
            Configuration.remote = "https://" + login + ":" + password + "@" + url;
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
