import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DynamicTable {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://testpages.eviltester.com/styled/index.html");

            driver.findElement(By.id("dynamictablestest")).click();
            WebElement table = driver.findElement(By.id("dynamictable"));
            List<MyObject> myObjects = objectList(table);
            for (int i = 0; i <= 5; i++) {
                String is = String.valueOf(i);
                MyObject obj = new MyObject(is, is);
                myObjects.add(obj);
            }

            driver.findElement(By.xpath("//details")).click();
            WebElement textA = driver.findElement(By.id("jsondata"));
            textA.clear();
            textA.sendKeys(serializeListToJson(myObjects));

            WebElement caption = driver.findElement(By.id("caption"));
            caption.clear();
            caption.sendKeys("New dynamic table");

            WebElement tableId = driver.findElement(By.id("tableid"));
            tableId.clear();
            tableId.sendKeys("newId");

            driver.findElement(By.id("refreshtable")).click();

            WebElement table2 = driver.findElement(By.id("newId"));
            List<MyObject> myObjects2 = objectList(table2);
            System.out.println(serializeListToJson(myObjects2));
            

            Thread.sleep(3000);
        } catch(InterruptedException e) {
            System.out.println(e);
        } finally {
            driver.quit();
        }
    }

    private static List<MyObject> objectList(WebElement table) {
        List<MyObject> myObjects = new ArrayList<MyObject>();
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int count = 0;

        for (WebElement row : rows) {
            MyObject myObject = new MyObject();
            List<WebElement> cells = row.findElements(By.tagName("td"));
            
            for (WebElement cell: cells) {
                if(count % 2 == 0) {
                    myObject.name = cell.getText();
                    count++;
                    continue;
                }
                myObject.age = cell.getText();
                myObjects.add(myObject);
                count++;
            }
        }
        return myObjects;
    }

    private static String serializeListToJson(List<MyObject> myObjects) {
        StringBuilder jsonBuilder = new StringBuilder("[");
        
        for (MyObject obj : myObjects) {
            // Append the JSON representation of each object
            jsonBuilder.append("{")
                    .append("\"name\": \"").append(obj.getName()).append("\", ")
                    .append("\"age\": ").append(obj.getAge())
                    .append("}, ");
        }

        // Remove the trailing comma and space
        if (jsonBuilder.length() > 1) {
            jsonBuilder.setLength(jsonBuilder.length() - 2);
        }

        // Close the JSON array
        jsonBuilder.append("]");

        return jsonBuilder.toString();
    }

    static class MyObject {
        private String name;
        private String age;
    
        public MyObject(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public MyObject() {

        }
    
        public String getName() {
            return name;
        }
    
        public String getAge() {
            return age;
        }
    }
}
