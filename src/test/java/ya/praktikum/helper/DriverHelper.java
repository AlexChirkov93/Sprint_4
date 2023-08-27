package ya.praktikum.helper;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.time.Duration;

import static ya.praktikum.helper.EnvConfig.WEB_DRIVER_CHROME;
import static ya.praktikum.helper.EnvConfig.WEB_DRIVER_FIREFOX;

public class DriverHelper extends ExternalResource {

    WebDriver driver;

    @Override
    protected void before() {
        if ("firefox".equals(System.getProperty("browser")))
            setUpFirefox();
        else
            setUpChrome();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private void setUpChrome() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(WEB_DRIVER_CHROME))
                .build();
        driver = new ChromeDriver(service);
    }

    public void setUpFirefox() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        var service = new GeckoDriverService.Builder()
                .usingDriverExecutable(new File(WEB_DRIVER_FIREFOX))
                .build();
        driver = new FirefoxDriver(service);
    }



    @Override
    protected void after() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}

