package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Homepages 
{                                                                           //it is page object layer - it consists of locators,constructors,methods,,,
    private WebDriver driver;
    private WebDriverWait wait;

    // 1. Define Locators using By
    private final By searchBox = By.id("search-box-input");
    private final By searchDropDown = By.cssSelector("div[class='sort-selected']");

    // 2. Constructor
    public Homepages(WebDriver driver) 
    {
        this.driver = driver;
        // Standard wait to ensure elements are ready before interaction
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 3. Action Methods
    public void searchProduct(String productName)
    {
        // Use wait to ensure the element is visible and interactable
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        element.clear();
        element.sendKeys(productName + Keys.ENTER);
    }
}