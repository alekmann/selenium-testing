import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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
            
            Thread.sleep(3000);
            
        } catch(InterruptedException e) {
            System.out.println(e);
        } finally {
            driver.close();
        }
    }
}
