import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to Google
            driver.get("https://www.google.com");

            // Wait for the Accept Cookies banner to appear, and click
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("L2AGLb")));
            button.click();

            // Search for selenium
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Selenium");
            searchBox.submit();

            // Wait for the search results page to load
            wait.until(ExpectedConditions.titleContains("Selenium"));

            Thread.sleep(5000);

        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
