import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToolShop {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://practicesoftwaretesting.com/#/");

            // Sort
            Select sort = new Select(driver.findElement(By.xpath("//select[@data-test='sort']"))) ;
            sort.selectByValue("name,asc");

            // Price range
            WebElement sliderHandle = driver.findElement(By.className("ngx-slider-pointer-min"));
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(sliderHandle, 20, 0).build().perform(); 

            // Search
            driver.findElement(By.xpath("//input[@data-test='search-query']")).sendKeys("pliers");
            driver.findElement(By.xpath("//button[@data-test='search-submit']")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[@data-test='search-reset']")).click();

            // Check box sort
            clickCheckboxes(driver);

            // Click card
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            List<WebElement> cards = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(".//a[@class='card']"), 0));
            WebElement firsCard = wait.until(ExpectedConditions.elementToBeClickable(cards.get(0)));
            firsCard.click();

            // add 100 to cart
            String toolName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@data-test='product-name']"))).getText();
            WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input")));
            input.clear();
            input.sendKeys("100");
            driver.findElement(By.xpath(".//button[@data-test='add-to-cart']")).click();

            // Check shopping cart information
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//a[@data-test='nav-cart']"))).click();
            String productTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-title"))).getText();
            System.out.println(productTitle);
            System.out.println("Found the right tool: " + productTitle.trim().equals(toolName.trim()));


            
            Thread.sleep(3000);
            
        } catch(InterruptedException e) {
            System.out.println(e);
        } finally {
            driver.close();
        }
    }

    static void clickCheckboxes(WebDriver driver) {
        List<WebElement> outerCB = driver.findElements(By.xpath("//div[@class='checkbox']"));
        for(WebElement outerCBElement : outerCB) {
            List<WebElement> innerCB = outerCBElement.findElements(By.xpath(".//input[@type='checkbox']"));

            for(WebElement innerCBElement : innerCB) {
                innerCBElement.click();
            }
        }
    }
}
