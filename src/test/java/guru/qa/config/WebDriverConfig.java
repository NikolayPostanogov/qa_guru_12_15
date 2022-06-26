package guru.qa.config;

import org.aeonbits.owner.Config;

import java.net.URL;

public interface WebDriverConfig extends Config {
    @Key("browser")
    @DefaultValue("CHROME")
    Browser getBrowser();

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    String getBaseUrl();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("browserVersion")
    @DefaultValue("103.0.5060.53")
    String getBrowserVersion();

    @Key("engine")
    @DefaultValue("local")
    String getEngine();

    @Key("remoteUrl")
    @DefaultValue("selenoid.autotests.cloud/wd/hub")
    String getRemoteUrl();
}
