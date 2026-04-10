
package base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;

import utility.configReader;
import utility.BrowserFactory;

public class BaseTest{

    public WebDriver driver;
    private configReader config;

    @Parameters({"browser"})
    @BeforeClass
    public void setup(String browserName) throws IOException {
        config = new configReader();

        if (browserName == null || browserName.isEmpty()) {
            browserName = config.getProperty("browser");
        }

        System.out.println("Running on browser: " + browserName);

        driver = BrowserFactory.initDriver(browserName);

        driver.get(config.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
