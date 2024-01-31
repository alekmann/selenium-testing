import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NestedFrames {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://testpages.eviltester.com/styled/index.html");
            driver.findElement(By.id("framestest")).click();

            printListElements(driver, "left", true);
            printListElements(driver, "middle", true);
            printListElements(driver, "right", false);

            Thread.sleep(3000);
        } catch(InterruptedException e) {
            System.out.println(e);
        } finally {
            driver.quit();
        }
    }

    static void printListElements(WebDriver driver, String name, boolean scroll) {
        driver.switchTo().frame(name);

        if(scroll) {
            WebElement scrollEl = driver.findElement(By.cssSelector("ul"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].parentElement.scrollTop += 500;", scrollEl);
        }

        List<WebElement> li = driver.findElements(By.tagName("li"));
        System.out.println("\n" + name + " list elements: \n");
        for (WebElement liEl : li) {
            System.out.println(liEl.getText());
        }
        driver.switchTo().defaultContent();
    }
    
}
