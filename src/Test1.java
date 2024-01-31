import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Test1 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to Google
            driver.get("https://automationintesting.com/selenium/testpage/?");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement firstName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("firstname")));
            firstName.sendKeys("Aleks");

            driver.findElement(By.id("surname")).sendKeys("Balek");

            Select gender = new Select(driver.findElement(By.id("gender")));
            gender.selectByValue("male");

            driver.findElement(By.id("blue")).click();

            driver.findElement(By.id("checkbox1")).click();
            driver.findElement(By.id("checkbox2")).click();


            driver.findElement(By.cssSelector("textarea[placeholder='Tell us some fun stuff!']")).sendKeys("Dette er fun");

            Select continent = new Select(driver.findElement(By.id("continent")));
            continent.selectByValue("south_america");

            Thread.sleep(3000);

            driver.findElement(By.id("submitbutton")).click();

            Thread.sleep(3000);


            
        }catch(InterruptedException e){
            System.out.println(e);
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
