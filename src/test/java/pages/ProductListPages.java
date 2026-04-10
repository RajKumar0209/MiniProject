package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductListPages {

    private WebDriver driver;
    private WebDriverWait wait;

    // 1. Define Locators using By
    private By sortDropdown = By.cssSelector("div[class='sort-selected']");
    private By relevanceOption = By.cssSelector("li[data-sorttype='rlvncy']");
    private By popularityOption = By.cssSelector("li[data-sorttype='plrty']");
    private By minRangeInput = By.cssSelector("input[name='fromVal']");
    private By maxRangeInput = By.cssSelector("input[name='toVal']");
    private By priceGoButton = By.cssSelector("div[class='price-go-arrow btn btn-line btn-theme-secondary']");
    private By productListing = By.className("product-tuple-listing");
    private By productTitle = By.className("product-title");
    private By productPrice = By.className("product-price");

    public ProductListPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void applyFilters(String min, String max) throws InterruptedException {
        // Interact with Sort Dropdown
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(relevanceOption)).click();
        
        // Re-open/interact for Popularity
        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(popularityOption)).click();

        // Handle Price Range
        WebElement minInput = wait.until(ExpectedConditions.visibilityOfElementLocated(minRangeInput));
        minInput.clear();
        minInput.sendKeys(min);

        WebElement maxInput = driver.findElement(maxRangeInput);
        maxInput.clear();
        maxInput.sendKeys(max);

        driver.findElement(priceGoButton).click();

        // Wait for results to refresh
        wait.until(ExpectedConditions.presenceOfElementLocated(productTitle));
        Thread.sleep(3000); 
        
    }

    public void printTopFive() {
        System.out.println("--- Top 5 Bluetooth Headphones ---");

        // Fetch the list of products
        List<WebElement> freshProducts = driver.findElements(productListing);

        int count = Math.min(freshProducts.size(), 5);

        for (int i = 0; i < count; i++) {
            try {
                // Re-finding elements within the loop helps prevent StaleElementReferenceException
                WebElement product = driver.findElements(productListing).get(i);

                String title = product.findElement(productTitle).getText();
                String price = product.findElement(productPrice).getText();

                System.out.println((i + 1) + ". " + title + " | Price: " + price);
            } catch (Exception e) {
                System.out.println("Error fetching product " + (i + 1) + ". Skipping...");
            }
        }
        System.out.println("-----------------------------------");
    }
}